package com.hexagonalmultimodule.orderservices.query;

import com.hexagonalmultimodule.orderapi.service.PricingOrderSearchService;
import com.hexagonalmultimodule.orderapi.model.PricingOrderSnapshot;
import com.hexagonalmultimodule.orderservices.port.PricingOrderSnapshotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultPricingOrderSearchService implements PricingOrderSearchService {
    private final PricingOrderSnapshotRepository pricingOrderSnapshotRepository;

    public List<PricingOrderSnapshot> findAllByCustomerId(String customerId) {
        return pricingOrderSnapshotRepository.findAllByCustomerId(customerId);
    }
}
