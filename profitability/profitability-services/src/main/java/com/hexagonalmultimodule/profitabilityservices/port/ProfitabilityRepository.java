package com.hexagonalmultimodule.profitabilityservices.port;

import com.hexagonalmultimodule.profitabilityapi.model.ProfitabilityDto;

public interface ProfitabilityRepository {
    Long create();

    ProfitabilityDto get(Long profitabilityId);

    void invalidate(Long profitabilityId);
}
