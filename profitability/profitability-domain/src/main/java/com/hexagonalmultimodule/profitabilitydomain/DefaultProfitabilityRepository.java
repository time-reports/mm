package com.hexagonalmultimodule.profitabilitydomain;

import com.hexagonalmultimodule.profitabilityapi.model.ProfitabilityDto;
import com.hexagonalmultimodule.profitabilitydomain.model.Profitability;
import com.hexagonalmultimodule.profitabilityservices.port.ProfitabilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class DefaultProfitabilityRepository implements ProfitabilityRepository {
    private final DbProfitabilityRepository profitabilityRepository;

    @Override
    public Long create() {
        return profitabilityRepository.save(new Profitability()).getId();
    }

    @Override
    @Transactional
    public void invalidate(Long profitabilityId) {
        profitabilityRepository.findById(profitabilityId)
                .ifPresent(Profitability::invalidate);
    }

    @Override
    public ProfitabilityDto get(Long profitabilityId) {
        return profitabilityRepository.findById(profitabilityId)
                .map(entity -> ProfitabilityDto.builder()
                        .data(entity.getData())
                        .build())
                .orElseThrow();
    }
}
