package com.hexagonalmultimodule.profitabilitydomain.model;

import com.hexagonalmultimodule.dddcommons.Aggregate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Aggregate
@Entity
@Data
@AllArgsConstructor
public class Profitability {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String data;
    private String status;

    public Profitability() {
        this.status = "CREATED";
    }

    public void invalidate() {
        this.status = "INVALID";
    }
}
