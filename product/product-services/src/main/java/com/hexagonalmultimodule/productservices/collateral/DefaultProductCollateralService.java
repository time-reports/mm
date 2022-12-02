package com.hexagonalmultimodule.productservices.collateral;

import com.hexagonalmultimodule.collateralcommons.CollateralForm;
import com.hexagonalmultimodule.productport.ProductCollateralFactory;
import com.hexagonalmultimodule.productport.ProductUpdateCollateralService;
import com.hexagonalmultimodule.productservices.port.WithCollateralsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@RequiredArgsConstructor
public class DefaultProductCollateralService {
    private final ProductCollateralFactory productCollateralFactory;
    private final WithCollateralsRepository withCollateralsRepository;
    private final ProductUpdateCollateralService updateCollateralService;

    @PostMapping("/collateral")
    public Long addCollateralToProduct(Long productId, CollateralForm collateralForm) {
        Long collateralId = productCollateralFactory.create(collateralForm);
        withCollateralsRepository.addCollateral(productId, collateralId);
        return collateralId;
    }

    @PostMapping("/collateral")
    public void updateCollateral(Long productId, CollateralForm collateralForm) {
        updateCollateralService.update(collateralForm);
        withCollateralsRepository.handleCollateralChange(productId, collateralForm);
    }
}
