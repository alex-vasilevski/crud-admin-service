package com.bank.service.crud.internal;

import com.bank.domain.Administrator;
import com.bank.repository.AdministratorRepository;
import com.bank.service.crud.spi.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdministratorService implements CrudService<Administrator, Long> {

    @Autowired
    private AdministratorRepository repository;

    @Override
    public void create(Administrator administrator) {
        repository.save(administrator);
    }

    @Override
    public Optional<Administrator> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Administrator> findAll() {
        List<Administrator> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list;
    }

    @Override
    public Optional<Administrator> update(Long id, Administrator sourceDto) {
        Optional<Administrator> optional = repository.findById(id);
        optional.ifPresent(target -> update(sourceDto, target));
        return optional;
    }

    private Administrator update(Administrator source, Administrator target){
        target.setName(source.getName());
        target.setLastName(source.getLastName());
        return target;
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
