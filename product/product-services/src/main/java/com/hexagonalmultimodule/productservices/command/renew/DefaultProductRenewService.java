package com.hexagonalmultimodule.productservices.command.renew;

import com.hexagonalmultimodule.productapi.renew.ProductRenewService;
import com.hexagonalmultimodule.productport.ProductCollateralRenewService;
import com.hexagonalmultimodule.productservices.port.RenewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class DefaultProductRenewService implements ProductRenewService {
    private final RenewRepository renewRepository;
    private final ProductCollateralRenewService productCollateralRenewService;

    @Transactional
    public Long renewById(Long renewalableId) {
        Long renewalId = renewRepository.renewById(renewalableId);
        productCollateralRenewService.renewByProductIds(renewalableId, renewalId);
        return renewalId;
    }
}
