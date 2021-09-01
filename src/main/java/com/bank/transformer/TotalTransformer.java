package com.bank.transformer;

import com.bank.domain.TotalEntity;
import com.bank.dto.Total;

public class TotalTransformer implements Transformer<TotalEntity, Total> {
    @Override
    public Total toDto(TotalEntity totalEntity) {
        return null;
    }

    @Override
    public TotalEntity toEntity(Total total) {
        return null;
    }
}
