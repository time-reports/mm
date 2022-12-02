package com.hexagonalmultimodule.productapi.snapshot;

import com.hexagonalmultimodule.decisionport.product.DecisionProductSearchService;
import com.hexagonalmultimodule.decisionport.product.model.DecisionProduct;
import com.hexagonalmultimodule.orderport.OrderProductSearchService;
import com.hexagonalmultimodule.orderport.product.model.OrderProduct;

import java.util.List;
import java.util.stream.Collectors;

public interface ProductSnapshotService extends OrderProductSearchService, DecisionProductSearchService {
    ProductSnapshot findByProductId(Long productId);

    default List<OrderProduct> findAllByOrderProductId(List<Long> productIds) {
        return findAllByProductId(productIds)
                .stream()
                .map(OrderProduct.class::cast)
                .collect(Collectors.toList());
    }

    default List<DecisionProduct> findAllByDecisionProductId(List<Long> productIds) {
        return findAllByProductId(productIds)
                .stream()
                .map(DecisionProduct.class::cast)
                .collect(Collectors.toList());
    }

    List<ProductSnapshot> findAllByProductId(List<Long> productIds);

}
