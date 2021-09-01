package com.bank.transformer;

import com.bank.domain.CardEntity;
import com.bank.dto.Card;

public class CardTransformer implements Transformer<CardEntity, Card> {
    @Override
    public Card toDto(CardEntity cardEntity) {
        return null;
    }

    @Override
    public CardEntity toEntity(Card card) {
        return null;
    }
}
