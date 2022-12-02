package com.hexagonalmultimodule.decisiondomain.model;

import com.hexagonalmultimodule.decisionport.order.model.DecisionPricingOrder;
import com.hexagonalmultimodule.decisionport.product.model.DecisionProduct;
import com.hexagonalmultimodule.decisionport.profitability.model.DecisionProfitability;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Builder
public class Decision {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String poType;
    private String profitabilityData;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> productTypes;

    public static Decision from(DecisionPricingOrder po, DecisionProfitability profitability, List<DecisionProduct> products) {
        return Decision.builder()
                .poType(po.getType())
                .profitabilityData(profitability.getData())
                .productTypes(products.stream().map(DecisionProduct::getType).collect(Collectors.toList()))
                .build();
    }
}
