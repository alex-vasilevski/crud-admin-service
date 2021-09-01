package com.bank.domain;

import com.bank.policy.currency.CurrencyType;
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
public class CurrencyEntity {
    @Id
    private Long id;
    private CurrencyType currencyType;
    private Long conversionIndex;

    public CurrencyEntity(CurrencyType currencyType, Long conversionIndex) {
        this.currencyType = currencyType;
        this.conversionIndex = conversionIndex;
    }
}
