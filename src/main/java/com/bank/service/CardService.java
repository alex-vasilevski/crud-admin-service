package com.bank.service;

import com.bank.domain.CardEntity;
import com.bank.dto.Card;
import com.bank.repository.CardRepository;
import com.bank.transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CardService implements CrudService<Card, Long>{

    @Autowired
    private CardRepository repository;

    @Autowired
    private CardTransformer transformer;

    @Override
    public void create(Card card) {
        repository.save(transformer.toEntity(card));
    }

    @Override
    public Optional<Card> findById(Long id) {
        return repository.findById(id).map(transformer::toDto);
    }

    @Override
    public Iterable<Card> findAll() {
        List<CardEntity> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list.stream().map(transformer::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<Card> update(Long id, Card sourceDto) {
        Optional<CardEntity> optional = repository.findById(id);
        CardEntity source = transformer.toEntity(sourceDto);
        optional.ifPresent(target -> update(source, target));
        return optional.map(transformer::toDto);
    }

    private CardEntity update(CardEntity source, CardEntity target){
        target.setAccount(source.getAccount());
        target.setNumber(source.getNumber());
        target.setOwner(source.getOwner());
        return target;
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
