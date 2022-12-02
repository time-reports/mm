package com.hexagonalmultimodule.collateralcommons;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class CollateralForm {
   Long id;
   String type;
   Long amount;
}
