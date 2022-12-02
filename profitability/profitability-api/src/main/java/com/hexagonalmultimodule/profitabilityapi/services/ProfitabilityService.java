package com.hexagonalmultimodule.profitabilityapi.services;

import com.hexagonalmultimodule.decisionport.profitability.DecisionProfitabilitySearchService;
import com.hexagonalmultimodule.orderport.profitability.OrderProfitabilityService;
import com.hexagonalmultimodule.profitabilityapi.model.ProfitabilityDto;

public interface ProfitabilityService extends OrderProfitabilityService, DecisionProfitabilitySearchService {
    void invalidate(Long profitabilityId);

    Long create();

    ProfitabilityDto get(Long profitabilityId);
}
