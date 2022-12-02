package com.hexagonalmultimodule.profitabilitydomain;

import com.hexagonalmultimodule.profitabilitydomain.model.Profitability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DbProfitabilityRepository extends JpaRepository<Profitability, Long> {
}
