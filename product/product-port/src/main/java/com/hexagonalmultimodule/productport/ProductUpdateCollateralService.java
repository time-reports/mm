package com.hexagonalmultimodule.productport;

import com.hexagonalmultimodule.collateralcommons.CollateralForm;
import com.hexagonalmultimodule.productcommons.ProductForm;

public interface ProductUpdateCollateralService {
    void update(CollateralForm collateralForm);
    void updateCollaterals(ProductForm form);
}
