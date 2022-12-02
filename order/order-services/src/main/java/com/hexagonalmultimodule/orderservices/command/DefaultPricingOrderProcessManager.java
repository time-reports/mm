package com.hexagonalmultimodule.orderservices.command;

import com.hexagonalmultimodule.orderapi.model.ProfitabilityParent;
import com.hexagonalmultimodule.orderapi.service.PricingOrderProcessManager;
import com.hexagonalmultimodule.orderport.OrderProductSearchService;
import com.hexagonalmultimodule.orderport.product.OrderProductFactory;
import com.hexagonalmultimodule.orderport.product.OrderProductRemoveService;
import com.hexagonalmultimodule.orderport.product.OrderProductRenewService;
import com.hexagonalmultimodule.orderport.product.model.OrderProduct;
import com.hexagonalmultimodule.orderport.product.model.WithCustomerProducts;
import com.hexagonalmultimodule.orderport.profitability.OrderProfitabilityService;
import com.hexagonalmultimodule.orderservices.port.PricingOrderSnapshotRepository;
import com.hexagonalmultimodule.orderservices.port.WithCustomerProductsRepository;
import com.hexagonalmultimodule.productcommons.ProductForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Klasa odpowiedzialna tylko za logikę koordynacyjną (SRP) modułów przechoujących stan
 * - komponent wie co musi się zadziać w ramach ACID - atomicity, consistency, isolation, durability
 * na poziomeie integracji miedzymodułowej
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class DefaultPricingOrderProcessManager implements PricingOrderProcessManager {
    private final OrderProductFactory productFactory;
    private final OrderProductSearchService productSearchService;
    private final OrderProductRenewService productRenewService;
    private final OrderProductRemoveService productRemoveService;
    private final OrderProfitabilityService profitabilityService;
    private final WithCustomerProductsRepository withCustomerProductsRepository;
    private final PricingOrderSnapshotRepository pricingOrderSnapshotRepository;

    @Override
    public Long createOrderFor(String customerId) {
        Long profitabilityId = profitabilityService.create();
        Long pricingOrderId = withCustomerProductsRepository.createFor(customerId, profitabilityId);
        return pricingOrderId;
    }

    @Override
    public Long addProduct(String customerId, ProductForm productForm) {
        validateProducts(customerId);
        Long productId = productFactory.create(productForm);
        withCustomerProductsRepository.addProduct(customerId, productId, productForm.getType());
        this.invalidateProfitability(customerId);
        return productId;
    }

    @Override
    public void removeProduct(String customerId, Long productId) {
        productRemoveService.removeProduct(customerId, productId);
        WithCustomerProducts pricingOrder = withCustomerProductsRepository.findAllByCustomerId(customerId)
                .stream().findAny().orElseThrow();
        /**
         * zbędne upublicznienie * WithCustomerProducts::removeProduct * można zamknąć zachowanie w domenie
         * */
        pricingOrder.removeProduct(customerId, productId);
        this.invalidateProfitability(customerId);
    }

    @Override
    public void renewProduct(String customerId, Long productId) {
        Long renewalId = productRenewService.renewById(productId);
        OrderProduct renewal = productSearchService.findByProductId(renewalId);
        withCustomerProductsRepository.addProduct(customerId, productId, renewal.getType());
        this.invalidateProfitability(customerId);
    }

    private void invalidateProfitability(String customerId) {
        ProfitabilityParent profitabilityParent = pricingOrderSnapshotRepository.findAllByCustomerId(customerId)
                .stream().findAny().orElseThrow();
        profitabilityService.invalidate(profitabilityParent.getProfitabilityId());
    }

    /**
     * Efficiency, ACID !!!
     */
    @Deprecated
    private void validateProducts(String customerId) {
        List<Long> productIds = pricingOrderSnapshotRepository.findAllByCustomerId(customerId).stream()
                .flatMap(po -> po.getProductIds().stream()).toList();

        var isValid = productSearchService.findAllByOrderProductId(productIds).stream()
                .filter(p -> List.of("overdraft").contains(p.getType()))
                .collect(Collectors.groupingBy(OrderProduct::getType, Collectors.counting()))
                .values()
                .stream().allMatch(count -> count <= 0);
        if (!isValid) {
            throw new RuntimeException("SINGLE_IN_PRICING_ORDER_RULE: ");
        }
    }
}
