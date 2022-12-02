package com.hexagonalmultimodule.productdomain.service;

import com.hexagonalmultimodule.collateralcommons.CollateralForm;
import com.hexagonalmultimodule.dddcommons.DomainRepository;
import com.hexagonalmultimodule.productcommons.ProductForm;
import com.hexagonalmultimodule.productdomain.product.common.BaseProductData;
import com.hexagonalmultimodule.productdomain.product.overdraft.Overdraft;
import com.hexagonalmultimodule.productdomain.relation.ProductRelation;
import com.hexagonalmultimodule.productservices.port.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;

import javax.persistence.EntityNotFoundException;
import java.util.stream.Stream;

/**
 * Repozytorium odpowiada za logikę domenową Produktu i Relacji Produktowej
 * główne zadania:
 * - spójność pomiędzy obiektami BaseProductData i ProductRelation
 * - Enapsulacja (ukrycie modeli) BaseProductData i ProductRelation przed pakietem product-services
 * poprzez interfejsy CreateRepository, RemoveRepository, RenewRepository
 */
@DomainRepository
@Repository
@RequiredArgsConstructor
class DefaultProductRepository implements CreateRepository, RemoveRepository, RenewRepository, UpdateRepository, WithCollateralsRepository {
    private final BaseProductDataRepository baseProductDataRepository;
    private final RelationRepository relationRepository;

    @Override
    public Long create(ProductForm form) {
        return baseProductDataRepository.save(generateNewProduct(form)).getId();
    }

    @Override
    public void update(ProductForm form) {
        Stream.of(baseProductDataRepository.findById(form.getId()).orElseThrow(EntityNotFoundException::new))
                .peek(product -> product.updateFromForm(form))
                .forEach(baseProductDataRepository::save);
    }

    @Override
    public void deleteById(Long productId) {
        relationRepository.removeRelationsFor(productId);
        baseProductDataRepository.deleteById(productId);
    }

    public Long renewById(Long renewalableId) {
        Renewalable renewalable = baseProductDataRepository.findRenewalableById(renewalableId).orElseThrow(EntityNotFoundException::new);
        Renewal renewal = renewalable.createRenewal();
        baseProductDataRepository.saveProduct(renewalable);
        Long renewalId = baseProductDataRepository.saveProduct(renewal);

        relationRepository.addRelation(ProductRelation.from("RENEW", renewalableId, renewalId));
        return renewalId;
    }

    @Override
    public void addCollateral(Long productId, Long collateralId) {
        Stream.of(baseProductDataRepository.findWithCollaterals(productId).orElseThrow(EntityNotFoundException::new))
                .peek(withCollaterals -> withCollaterals.addCollateral(collateralId))
                .forEach(baseProductDataRepository::saveProduct);
    }

    @Override
    public void handleCollateralChange(Long productId, CollateralForm collateralForm) {
        Stream.of(baseProductDataRepository.findCollateralChangeAware(productId).orElseThrow(EntityNotFoundException::new))
                .peek(product -> product.onCollateralChange(collateralForm))
                .forEach(baseProductDataRepository::saveProduct);
    }

    private static BaseProductData generateNewProduct(ProductForm form) {
        switch (form.getType()) {
            case "overdraft":
                return Overdraft.from(form);
            default:
                throw new UnsupportedOperationException("unknown type");
        }
    }

}
