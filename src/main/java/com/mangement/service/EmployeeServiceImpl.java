package com.mangement.service;

import com.mangement.dto.Employee;
import com.mangement.dto.Project;
import com.mangement.transformers.Converter;
import com.mangement.exception.ProjectNotFoundException;
import com.mangement.domain.EmployeeEntity;
import com.mangement.exception.EmployeeNotFoundException;
import com.mangement.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    @Qualifier("projectServiceImpl")
    private ProjectService projectService;

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    @Qualifier("employeeTransformer")
    private Converter<EmployeeEntity, Employee> converter;



    @Autowired
    private PageableProducer pageableProducer;

    @Override
    public void create(Employee employee) {
        logger.info("trying to create employee: " + employee.toString());

        EmployeeEntity entity = converter.toEntity(employee);

        repository.save(entity);
    }

    @Override
    public Employee findById(Long id) throws EmployeeNotFoundException {
        logger.info("trying to find employee with id " + id);

        EmployeeEntity entity = repository
                .findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("employee with id " + id + " not found"));

        //return conversionService.convert(entity, Employee.class);
        return converter.toDto(entity);
    }

    @Override
    public Page<Employee> findAllMatchingAndSort(Employee searchCriteria, Pageable pageable) throws EmployeeNotFoundException {
        EmployeeEntity criteria = converter.toEntity(searchCriteria);

        logger.info("trying to find matching employee by following params: " + searchCriteria);

        Page<EmployeeEntity> searchResults = repository.findAll(Example.of(criteria), pageable);

        if(searchResults.getTotalElements() == 0)
            throw new EmployeeNotFoundException("no matching results");

        return searchResults.map(converter::toDto);
    }

    @Override
    public Page<Employee> findAllOnProject(Long projectId, Pageable pageable) throws EmployeeNotFoundException, ProjectNotFoundException {
        logger.info("trying to find all employees by on project with id " + projectId);

        Project project = projectService.findById(projectId);
        List<Employee> employees = project.employees().stream().toList();
        if (employees.isEmpty())
            throw new EmployeeNotFoundException("no employees found on project with id " + projectId);

        return new PageImpl<Employee>(employees, pageable, employees.size());
    }

    @Override
    public Employee update(Long id, Employee employee) throws EmployeeNotFoundException {
        logger.info("trying to update employee with id + " + id + " with following value: " + employee.toString());

        Optional<EmployeeEntity> optional = repository.findById(id);
        EmployeeEntity target = optional.orElseThrow(() -> new EmployeeNotFoundException("employee with id " + id + " not found"));
        EmployeeEntity source = converter.toEntity(employee);
        target = update(target, source);
        return converter.toDto(target);
    }

    private EmployeeEntity update(EmployeeEntity source, EmployeeEntity target){
        target.setSalary(source.getSalary());
        target.setDivision(source.getDivision());
        target.setRole(source.getRole());
        target.setPersonalInformation(source.getPersonalInformation());
        return target;
    }

    @Override
    public void deleteById(Long id) {
        logger.info("trying to delete employee with id " + id);
        repository.deleteById(id);
    }
}
