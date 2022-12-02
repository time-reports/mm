package com.hexagonalmultimodule.decisionport.order.model;

import java.util.List;

public interface DecisionPricingOrder {
    Long getProfitabilityId();

    String getType();

    List<Long> getProductIds();
}
