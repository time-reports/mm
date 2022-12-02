package com.hexagonalmultimodule.productapi.snapshot;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OverdraftSnapshot implements ProductSnapshot {
    private Long id;
    private Long amount;
    private String type;
}
