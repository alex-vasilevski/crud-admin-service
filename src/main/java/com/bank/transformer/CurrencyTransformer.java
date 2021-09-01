package com.bank.transformer;

import com.bank.domain.CurrencyEntity;
import com.bank.dto.Currency;

public class CurrencyTransformer implements Transformer<CurrencyEntity, Currency> {
    @Override
    public Currency toDto(CurrencyEntity currencyEntity) {
        return null;
    }

    @Override
    public CurrencyEntity toEntity(Currency currency) {
        return null;
    }
}
