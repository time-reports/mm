package com.hexagonalmultimodule.collateralservice;

import com.hexagonalmultimodule.collateralapi.CollateralService;
import com.hexagonalmultimodule.collateralcommons.CollateralForm;
import com.hexagonalmultimodule.productcommons.ProductForm;
import org.springframework.stereotype.Service;

@Service
public class DefaultCollateralService implements CollateralService {

    @Override
    public Long create(CollateralForm form) {
        System.out.println("create type: " + form.getType());
        return 1L;
    }

    @Override
    public void update(CollateralForm collateralForm) {

    }

    @Override
    public void renewByProductIds(Long renewableId, Long renewalId) {
        System.out.println("renewByProductIds for  renewableId: " + renewableId + " renewalId : " + renewalId);
    }

    @Override
    public void removeByProductId(Long productId) {
        System.out.println("removeByProductId: " + productId);
    }

    @Override
    public void updateCollaterals(ProductForm form) {
        System.out.println("removeByProductId: " + form);
    }
}
