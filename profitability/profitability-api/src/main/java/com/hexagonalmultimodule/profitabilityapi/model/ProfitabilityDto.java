package com.hexagonalmultimodule.profitabilityapi.model;

import com.hexagonalmultimodule.decisionport.profitability.model.DecisionProfitability;
import com.hexagonalmultimodule.orderport.profitability.model.OrderProfitability;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ProfitabilityDto implements OrderProfitability, DecisionProfitability {
    private final String data;
}
