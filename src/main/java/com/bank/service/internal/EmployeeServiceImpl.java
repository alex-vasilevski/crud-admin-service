package com.bank.service.internal;

import com.bank.domain.Employee;
import com.bank.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl {

    private static final int PAGE_SIZE = 10;

    @Autowired
    private EmployeeRepository repository;

    public void create(Employee employee) {
        repository.save(employee);
    }

    public Optional<Employee> findById(Long id) {
        return repository.findById(id);
    }

    public Page<Employee> findAll() {
        Pageable pageable = PageRequest.ofSize(PAGE_SIZE);

        return repository.findAll(pageable);
    }

    public Page<Employee> findAllAndSort(String direction, List<String> sortParams) {
        String[] params = new String[sortParams.size()];
        sortParams.toArray(params);
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), params);
        Pageable pageable = PageRequest.ofSize(PAGE_SIZE).withSort(sort);
        return repository.findAll(pageable);
    }

    public Page<Employee> findAllMatching(Employee employee) {
        Pageable pageable = PageRequest.ofSize(PAGE_SIZE);
        return repository.findAll(Example.of(employee), pageable);
    }


    public Page<Employee> findAllMatchingAndSort(Employee employee, String direction, List<String> sortParams){
        String[] params = new String[sortParams.size()];
        sortParams.toArray(params);
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), params);
        Pageable pageable = PageRequest.ofSize(PAGE_SIZE).withSort(sort);
        return repository.findAll(Example.of(employee), pageable);
    }

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

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
