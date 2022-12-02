package com.hexagonalmultimodule.orderport.product;

import com.hexagonalmultimodule.productcommons.ProductForm;

public interface OrderProductFactory {
    Long create(ProductForm type);
}
