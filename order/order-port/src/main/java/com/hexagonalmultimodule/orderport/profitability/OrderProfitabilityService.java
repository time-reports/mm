package com.hexagonalmultimodule.orderport.profitability;

import com.hexagonalmultimodule.orderport.profitability.model.OrderProfitability;

public interface OrderProfitabilityService {
    Long create();

    void invalidate(Long profitabilityId);

    OrderProfitability get(Long profitabilityId);
}
