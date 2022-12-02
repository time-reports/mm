package com.hexagonalmultimodule.collateralapi;


import com.hexagonalmultimodule.collateralcommons.CollateralForm;
import com.hexagonalmultimodule.productport.*;

public interface CollateralService extends ProductCollateralFactory, ProductCollateralRenewService, ProductCollateralRemoveService, ProductUpdateCollateralService {
    Long create(CollateralForm type);

    void update(CollateralForm collateralForm);

    void renewByProductIds(Long renewableId, Long renewalId);

    void removeByProductId(Long productId);
}
