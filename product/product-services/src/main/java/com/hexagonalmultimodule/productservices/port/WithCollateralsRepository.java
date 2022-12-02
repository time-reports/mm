package com.hexagonalmultimodule.productservices.port;

import com.hexagonalmultimodule.collateralcommons.CollateralForm;

public interface WithCollateralsRepository {
    void addCollateral(Long productId, Long collateralId);

    void handleCollateralChange(Long productId, CollateralForm collateralForm);
}
