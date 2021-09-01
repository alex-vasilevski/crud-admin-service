package com.bank.service;

import com.bank.domain.TotalEntity;
import com.bank.dto.Total;
import com.bank.repository.TotalRepository;
import com.bank.transformer.TotalTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Deprecated
public class TotalService implements CrudService<Total, Long> {

    @Autowired
    private TotalTransformer transformer;
    @Autowired
    private TotalRepository repository;

    @Override
    public void create(Total total) {
        repository.save(transformer.toEntity(total));
    }

    @Override
    public Optional<Total> findById(Long id) {
        return repository.findById(id).map(transformer::toDto);
    }

    @Override
    public Iterable<Total> findAll() {
        List<TotalEntity> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list.stream().map(transformer::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<Total> update(Long id, Total sourceDto) {
        Optional<TotalEntity> optional = repository.findById(id);
        TotalEntity source = transformer.toEntity(sourceDto);
        optional.ifPresent(target -> update(source, target));
        return optional.map(transformer::toDto);
    }

    private TotalEntity update(TotalEntity source, TotalEntity target){
        target.setConventionalUnits(source.getConventionalUnits());
        target.setCurrencyEntity(source.getCurrencyEntity());
        return target;
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
