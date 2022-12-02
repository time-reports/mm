package com.hexagonalmultimodule.orderapi.service;


import com.hexagonalmultimodule.productcommons.ProductForm;

public interface PricingOrderProcessManager {
    Long createOrderFor(String customerId);
    Long addProduct(String customerId, ProductForm productForm);
    void removeProduct(String customerId, Long productId);
    void renewProduct(String customerId, Long productId);
}
