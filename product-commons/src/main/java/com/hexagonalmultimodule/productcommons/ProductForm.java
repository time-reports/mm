package com.hexagonalmultimodule.productcommons;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class ProductForm {
    private Long id;
    private Long amount;
    private String type;
}
