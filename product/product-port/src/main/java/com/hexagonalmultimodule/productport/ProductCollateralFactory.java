package com.hexagonalmultimodule.productport;

import com.hexagonalmultimodule.collateralcommons.CollateralForm;

public interface ProductCollateralFactory {
    Long create(CollateralForm type);
}
