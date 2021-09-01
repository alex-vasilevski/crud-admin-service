package com.bank.service;

import com.bank.domain.CurrencyEntity;
import com.bank.domain.operators.ClientEntity;
import com.bank.dto.Currency;
import com.bank.repository.CurrencyRepository;
import com.bank.transformer.CurrencyTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CurrencyService implements CrudService<Currency, Long> {

    @Autowired
    private CurrencyRepository repository;
    @Autowired
    private CurrencyTransformer transformer;

    @Override
    public void create(Currency currency) {
        repository.save(transformer.toEntity(currency));
    }

    @Override
    public Optional<Currency> findById(Long id) {
        return repository.findById(id).map(transformer::toDto);
    }

    @Override
    public Iterable<Currency> findAll() {
        List<CurrencyEntity> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list.stream().map(transformer::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<Currency> update(Long id, Currency sourceDto) {
        Optional<CurrencyEntity> optional = repository.findById(id);
        CurrencyEntity source = transformer.toEntity(sourceDto);
        optional.ifPresent(target -> update(source, target));
        return optional.map(transformer::toDto);
    }

    private CurrencyEntity update(CurrencyEntity source, CurrencyEntity target){
        target.setCurrencyType(source.getCurrencyType());
        target.setConversionIndex(source.getConversionIndex());
        return target;
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
