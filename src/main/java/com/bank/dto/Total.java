package com.bank.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Total {
    private Currency currencyEntity;
    private Integer conventionalUnits;

    public Total(Currency currencyEntity, Integer conventionalUnits) {
        this.currencyEntity = currencyEntity;
        this.conventionalUnits = conventionalUnits;
    }
}
