package com.bank.service;

import com.bank.domain.operators.AdministratorEntity;
import com.bank.dto.Administrator;
import com.bank.repository.AdministratorRepository;
import com.bank.transformer.AdministratorTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdministratorService implements CrudService<Administrator, Long> {

    @Autowired
    private AdministratorRepository repository;
    @Autowired
    private AdministratorTransformer transformer;

    @Override
    public void create(Administrator administrator) {
        repository.save(transformer.toEntity(administrator));
    }

    @Override
    public Optional<Administrator> findById(Long id) {
        return repository.findById(id).map(transformer::toDto);
    }

    @Override
    public List<Administrator> findAll() {
        List<AdministratorEntity> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list.stream().map(transformer::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<Administrator> update(Long id, Administrator sourceDto) {
        Optional<AdministratorEntity> optional = repository.findById(id);
        AdministratorEntity source = transformer.toEntity(sourceDto);
        optional.ifPresent(target -> update(source, target));
        return optional.map(transformer::toDto);
    }

    private AdministratorEntity update(AdministratorEntity source, AdministratorEntity target){
        target.setName(source.getName());
        target.setLastName(source.getLastName());
        target.setRights(source.getRights());
        return target;
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
