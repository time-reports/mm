package com.hexagonalmultimodule.productservices.port;

import com.hexagonalmultimodule.collateralcommons.CollateralForm;

public interface CollateralChangeAware {
    void onCollateralChange(CollateralForm collateralForm);
}
