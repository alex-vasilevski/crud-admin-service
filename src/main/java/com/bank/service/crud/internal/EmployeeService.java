package com.bank.service.crud.internal;

import com.bank.domain.Employee;
import com.bank.repository.EmployeeRepository;
import com.bank.service.crud.spi.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements CrudService<Employee, Long> {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public void create(Employee employee) {
        repository.save(employee);
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list;
    }

    @Override
    public Optional<Employee> update(Long id, Employee sourceDto) {
        Optional<Employee> optional = repository.findById(id);
        optional.ifPresent(target -> update(sourceDto, target));
        return optional;
    }

    private Employee update(Employee source, Employee target){
        target.setName(source.getName());
        target.setLastName(source.getLastName());
        return target;
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
