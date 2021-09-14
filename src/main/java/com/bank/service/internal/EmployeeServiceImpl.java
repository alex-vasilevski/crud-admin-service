package com.bank.service.internal;

import com.bank.api.dto.Employee;
import com.bank.api.transformers.spi.EmployeeTransformer;
import com.bank.api.transformers.spi.Transformer;
import com.bank.store.domain.EmployeeEntity;
import com.bank.exception.EmployeeNotFoundException;
import com.bank.store.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl {

    private static final int PAGE_SIZE = 10;
    private static final String DEFAULT_SORT_PARAMETER = "id";
    private static final String DEFAULT_SORT_DIRECTION = Sort.Direction.ASC.toString();

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private EmployeeTransformer transformer;

    public void create(Employee employee) {
        logger.info("trying to create employee: " + employee.toString());
        EmployeeEntity entity = transformer.toEntity(employee);
        repository.save(entity);
    }

    public Employee findById(Long id) throws EmployeeNotFoundException {
        logger.info("trying to find employee with id " + id);
        EmployeeEntity entity = repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("employee with id " + id + " not found"));
        return transformer.toDto(entity);
    }

    public Page<Employee> findAllMatchingAndSort(Employee searchCriteria, String direction, Set<String> sortParams) throws EmployeeNotFoundException {

//        direction = Optional.ofNullable(direction).orElse(DEFAULT_SORT_DIRECTION);
//        sortParams = Optional.ofNullable(sortParams).orElse(Collections.singleton(DEFAULT_SORT_PARAMETER));

        if (direction == null)
            direction = DEFAULT_SORT_DIRECTION;

        if(sortParams == null)
            sortParams = Collections.singleton(DEFAULT_SORT_PARAMETER);

        String[] params = sortParams.toArray(new String[0]);

        Sort sort = Sort.by(Sort.Direction.valueOf(direction), params);
        Pageable pageable = PageRequest.ofSize(PAGE_SIZE).withSort(sort);
        EmployeeEntity criteria = transformer.toEntity(searchCriteria);

        logger.info("trying to find matching employee by following params: " + searchCriteria +
                "; using sorting by params " + Arrays.toString(sortParams.toArray())
                + ", with direction: " + direction);

        Page<EmployeeEntity> searchResults = repository.findAll(Example.of(criteria), pageable);
        if(searchResults.getTotalElements() == 0)
            throw new EmployeeNotFoundException("no matching results");

        return searchResults.map(transformer::toDto);
    }

    public Employee update(Long id, Employee employee) throws EmployeeNotFoundException {
        logger.info("trying to update employee with id + " + id + " with following value: " + employee.toString());

        Optional<EmployeeEntity> optional = repository.findById(id);
        EmployeeEntity target = optional.orElseThrow(() -> new EmployeeNotFoundException("employee with id " + id + " not found"));
        EmployeeEntity source = transformer.toEntity(employee);
        target = update(target, source);
        return transformer.toDto(target);
    }

    private EmployeeEntity update(EmployeeEntity source, EmployeeEntity target){
        target.setName(source.getName());
        target.setLastName(source.getLastName());
        target.setAge(source.getAge());
        target.setBirthDay(source.getBirthDay());
        target.setSalary(source.getSalary());
        return target;
    }

    public void deleteById(Long id) {
        logger.info("trying to delete employee with id " + id);
        repository.deleteById(id);
    }

}
