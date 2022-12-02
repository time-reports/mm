package com.hexagonalmultimodule.productdomain.product.overdraft;

import com.hexagonalmultimodule.collateralcommons.CollateralForm;
import com.hexagonalmultimodule.dddcommons.Aggregate;
import com.hexagonalmultimodule.productapi.snapshot.OverdraftSnapshot;
import com.hexagonalmultimodule.productapi.snapshot.ProductSnapshot;
import com.hexagonalmultimodule.productcommons.ProductForm;
import com.hexagonalmultimodule.productdomain.product.common.BaseProductData;
import com.hexagonalmultimodule.productservices.port.CollateralChangeAware;
import com.hexagonalmultimodule.productservices.port.Renewal;
import com.hexagonalmultimodule.productservices.port.Renewalable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Aggregate
@Entity
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("overdraft")
public class Overdraft extends BaseProductData implements Renewalable, Renewal, CollateralChangeAware {
    private boolean renewed;
    private boolean renewal;

    public static Overdraft from(ProductForm form) {
        Overdraft overdraft = new Overdraft();
        overdraft.setType(form.getType());
        return overdraft;
    }

    @Override
    public Overdraft createRenewal() {
        Overdraft overdraft = new Overdraft();
        overdraft.setRenewal(true);
        overdraft.setAmount(getAmount());
        overdraft.setType(getType());
        markRenewed();
        return overdraft;
    }

    @Override
    public void onCollateralChange(CollateralForm collateralForm) {

    }

    private void markRenewed() {
        this.renewed = true;
    }

    public ProductSnapshot snapshot() {
        return OverdraftSnapshot.builder()
                .id(this.getId())
                .type(this.getType())
                .amount(this.getAmount())
                .build();
    }

}
