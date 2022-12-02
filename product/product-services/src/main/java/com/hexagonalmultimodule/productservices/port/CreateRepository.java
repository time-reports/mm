package com.hexagonalmultimodule.productservices.port;

import com.hexagonalmultimodule.productcommons.ProductForm;

public interface CreateRepository {
    Long create(ProductForm type);
}
