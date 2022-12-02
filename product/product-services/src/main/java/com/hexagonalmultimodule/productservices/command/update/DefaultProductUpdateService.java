package com.hexagonalmultimodule.productservices.command.update;

import com.hexagonalmultimodule.productcommons.ProductForm;
import com.hexagonalmultimodule.productport.ProductUpdateCollateralService;
import com.hexagonalmultimodule.productservices.port.UpdateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

@Service
@RequiredArgsConstructor
public class DefaultProductUpdateService {
    private final UpdateRepository updateRepository;
    private final ProductUpdateCollateralService productUpdateCollateralService;

    @PutMapping()
    public void updateProduct(ProductForm form) {
        updateRepository.update(form);
        productUpdateCollateralService.updateCollaterals(form);
    }
}
