package com.hexagonalmultimodule.decisionport.profitability;

import com.hexagonalmultimodule.decisionport.profitability.model.DecisionProfitability;

public interface DecisionProfitabilitySearchService {
    DecisionProfitability get(Long profitabilityId);
}
