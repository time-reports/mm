package com.hexagonalmultimodule.decisiondomain;

import com.hexagonalmultimodule.decisiondomain.model.Decision;
import com.hexagonalmultimodule.decisionport.order.model.DecisionPricingOrder;
import com.hexagonalmultimodule.decisionport.product.model.DecisionProduct;
import com.hexagonalmultimodule.decisionport.profitability.model.DecisionProfitability;
import com.hexagonalmultimodule.decisionservices.port.DecisionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DefaultDecisionRepository implements DecisionRepository {
    private final DbDecisionRepository decisionRepository;

    @Override
    public Long createFrom(DecisionPricingOrder pricingOrder, DecisionProfitability profitability, List<DecisionProduct> products) {
        return decisionRepository.save(Decision.from(pricingOrder, profitability, products)).getId();
    }
}
