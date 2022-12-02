package com.hexagonalmultimodule.productservices.command.remove;

import com.hexagonalmultimodule.productapi.remove.ProductRemoveService;
import com.hexagonalmultimodule.productport.ProductCollateralRemoveService;
import com.hexagonalmultimodule.productservices.port.RemoveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultProductRemoveService implements ProductRemoveService {
    private final RemoveRepository removeRepository;
    private final ProductCollateralRemoveService productCollateralRemoveService;

    @Override
    public void removeProduct(String customerId, Long productId) {
        removeRepository.deleteById(productId);
        productCollateralRemoveService.removeByProductId(productId);
    }
}
