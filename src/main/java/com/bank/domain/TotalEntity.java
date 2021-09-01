package com.bank.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class TotalEntity {

    @Id
    private Long id;
    private CurrencyEntity currencyEntity;
    private Integer conventionalUnits;

    public TotalEntity(CurrencyEntity currencyEntity, Integer conventionalUnits) {
        this.currencyEntity = currencyEntity;
        this.conventionalUnits = conventionalUnits;
    }
}
