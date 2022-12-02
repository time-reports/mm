package com.hexagonalmultimodule.orderport;

import com.hexagonalmultimodule.orderport.product.model.OrderProduct;

import java.util.List;

public interface OrderProductSearchService {
    OrderProduct findByProductId(Long productId);
    List<OrderProduct> findAllByOrderProductId(List<Long> productIds);
}
