package com.hexagonalmultimodule.productapi.create;

import com.hexagonalmultimodule.orderport.product.OrderProductFactory;
import com.hexagonalmultimodule.productcommons.ProductForm;

public interface ProductFactory extends OrderProductFactory {
    Long create(ProductForm type);
}
