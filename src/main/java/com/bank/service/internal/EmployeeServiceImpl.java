package com.bank.service.internal;

import com.bank.domain.Employee;
import com.bank.exception.CreateEmployeeException;
import com.bank.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.Validator;

import java.util.*;
import java.util.stream.Stream;

@Service
public class EmployeeServiceImpl {

    private static final int PAGE_SIZE = 10;
    private static final String DEFAULT_SORT_PARAMETER = "id";
    private static final String DEFAULT_SORT_DIRECTION = Sort.Direction.ASC.toString();

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private Set<Validator> validators;

    public void create(Employee employee) throws CreateEmployeeException {
        Errors errors = new MapBindingResult(new HashMap<>(), "employee");
        Stream<Validator> supportedValidators = validators.stream().filter(validator -> validator.supports(Employee.class));
        supportedValidators.forEach(validator -> validator.validate(employee, errors));

        if(!errors.getAllErrors().isEmpty()){
            String message = errors.getAllErrors().get(0).getCode();
            throw new CreateEmployeeException(message);
        }
        repository.save(employee);
    }

    public Optional<Employee> findById(Long id) {
        return repository.findById(id);
    }

    public Page<Employee> findAllMatchingAndSort(Employee employee, String direction, Set<String> sortParams){

        if (direction == null){
            direction = DEFAULT_SORT_DIRECTION;
        }
        if(sortParams == null){
            sortParams = Collections.singleton(DEFAULT_SORT_PARAMETER);
        }
        String[] params = sortParams.toArray(new String[0]);

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
        target.setAge(source.getAge());
        target.setBirthDay(source.getBirthDay());
        target.setSalary(source.getSalary());
        return target;
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
