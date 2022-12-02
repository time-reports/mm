package com.hexagonalmultimodule.productdomain.service;


import com.hexagonalmultimodule.productdomain.product.common.BaseProductData;
import com.hexagonalmultimodule.productservices.port.CollateralChangeAware;
import com.hexagonalmultimodule.productservices.port.Renewal;
import com.hexagonalmultimodule.productservices.port.Renewalable;
import com.hexagonalmultimodule.productservices.port.WithCollaterals;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface BaseProductDataRepository extends JpaRepository<BaseProductData, Long> {
    default Optional<Renewalable> findRenewalableById(Long renewalId) {
        return findById(renewalId)
                .filter(p -> p instanceof Renewalable)
                .map(renewalable -> (Renewalable) renewalable);
    }

    default Optional<CollateralChangeAware> findCollateralChangeAware(Long productId) {
        return findById(productId)
                .filter(p -> p instanceof CollateralChangeAware)
                .map(renewalable -> (CollateralChangeAware) renewalable);
    }

    default Optional<WithCollaterals> findWithCollaterals(Long productId) {
        return findById(productId)
                .filter(p -> p instanceof WithCollaterals)
                .map(withCollaterals -> (WithCollaterals) withCollaterals);
    }

    default Long saveProduct(Object baseProductData) {
        if(baseProductData instanceof BaseProductData) {
            return save((BaseProductData) baseProductData).getId();
        } else {
            throw new UnsupportedOperationException();
        }
    }

}
