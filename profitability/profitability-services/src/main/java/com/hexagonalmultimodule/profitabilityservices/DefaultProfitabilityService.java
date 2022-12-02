package com.hexagonalmultimodule.profitabilityservices;

import com.hexagonalmultimodule.profitabilityapi.model.ProfitabilityDto;
import com.hexagonalmultimodule.profitabilityapi.services.ProfitabilityService;
import com.hexagonalmultimodule.profitabilityservices.port.ProfitabilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DefaultProfitabilityService implements ProfitabilityService {
    private final ProfitabilityRepository profitabilityRepository;

    @Override
    public Long create() {
        return profitabilityRepository.create();
    }

    @Override
    public void invalidate(Long profitabilityId) {
        profitabilityRepository.invalidate(profitabilityId);
    }

    @Override
    public ProfitabilityDto get(Long profitabilityId) {
        return profitabilityRepository.get(profitabilityId);
    }

}
