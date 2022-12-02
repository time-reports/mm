package com.hexagonalmultimodule.orderdomain.policies;

import com.hexagonalmultimodule.orderdomain.model.PricingOrder;
import com.hexagonalmultimodule.orderdomain.model.PricingOrderEntry;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OnlyOneProductPerPricingOrder implements PricingOrderPolicy {
    private static final List<String> SINGLE_IN_PRICING_ORDER_TYPES = List.of("overdraft");

    @Override
    public void validate(PricingOrder pricingOrder) {
        boolean isValid = pricingOrder.getEntries().stream()
                .peek(entry -> System.out.println("pType: " + entry.getProductType()))
                .filter(entry -> SINGLE_IN_PRICING_ORDER_TYPES.contains(entry.getProductType()))
                .collect(Collectors.groupingBy(PricingOrderEntry::getProductType, Collectors.counting()))
                .values()
                .stream().allMatch(count -> count <= 1);
        if (!isValid) {
            throw new RuntimeException("SINGLE_IN_PRICING_ORDER_RULE: " + pricingOrder.getEntries().stream().map(PricingOrderEntry::getProductType).toList());
        }
    }
}
