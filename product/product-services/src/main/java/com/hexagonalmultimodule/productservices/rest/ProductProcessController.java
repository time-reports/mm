package com.hexagonalmultimodule.productservices.rest;

import com.hexagonalmultimodule.collateralcommons.CollateralForm;
import com.hexagonalmultimodule.productcommons.ProductForm;
import com.hexagonalmultimodule.productservices.collateral.DefaultProductCollateralService;
import com.hexagonalmultimodule.productservices.command.update.DefaultProductUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
class ProductProcessController {
    private final DefaultProductUpdateService defaultProductUpdateService;
    private final DefaultProductCollateralService defaultProductCollateralService;

    @PutMapping()
    public void updateProduct(ProductForm form) {
        defaultProductUpdateService.updateProduct(form);
    }

    @PostMapping("/collateral")
    public Long addCollateralToProduct(Long productId, CollateralForm collateralForm) {
        return defaultProductCollateralService.addCollateralToProduct(productId, collateralForm);
    }

    @PutMapping("/collateral")
    public void updateCollateral(Long productId, CollateralForm collateralForm) {
        defaultProductCollateralService.addCollateralToProduct(productId, collateralForm);
    }
}
