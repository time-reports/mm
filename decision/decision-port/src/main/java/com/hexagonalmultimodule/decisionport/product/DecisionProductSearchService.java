package com.hexagonalmultimodule.decisionport.product;

import com.hexagonalmultimodule.decisionport.product.model.DecisionProduct;

import java.util.List;

public interface DecisionProductSearchService {
    List<DecisionProduct> findAllByDecisionProductId(List<Long> productIds);
}
