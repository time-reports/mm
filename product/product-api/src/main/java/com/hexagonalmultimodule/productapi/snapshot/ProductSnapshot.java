package com.hexagonalmultimodule.productapi.snapshot;

import com.hexagonalmultimodule.decisionport.product.model.DecisionProduct;
import com.hexagonalmultimodule.orderport.product.model.OrderProduct;

public interface ProductSnapshot extends OrderProduct, DecisionProduct {
    Long getId();
    Long getAmount();
    String getType();
}
