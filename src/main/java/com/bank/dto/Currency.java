package com.bank.dto;

import com.bank.policy.currency.CurrencyType;
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
public class Currency {

    private CurrencyType currencyType;
    private Long conversionIndex;

    public Currency(CurrencyType currencyType, Long conversionIndex) {
        this.currencyType = currencyType;
        this.conversionIndex = conversionIndex;
    }
}
