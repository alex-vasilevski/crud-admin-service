package com.bank.api.controllers.rest;

import com.bank.api.dto.v2.Employee;
import com.bank.store.domain.Division;
import com.bank.store.domain.Role;
import com.bank.exception.EmployeeNotFoundException;
import com.bank.service.EmployeeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.time.LocalDate;
import java.util.Set;

@RestController
@RequestMapping("/employees")
public class EmployeeController{

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    @Qualifier("employeeServiceImpl")
    private EmployeeServiceImpl service;

    @PostMapping
    public ResponseEntity<Employee> create(@Valid @RequestBody Employee employee) {
        logger.info("started to handle POST request");
        service.create(employee);
        return ResponseEntity.created(URI.create("/employees")).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@NotNull @PathVariable Long id) throws EmployeeNotFoundException {
        logger.info("started to handle GET request");
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<Employee>> findAllMatchingAndSort(@RequestParam(name = "name", required = false) String name,
                                                                 @RequestParam(name = "last_name", required = false) String lastName,
                                                                 @RequestParam(name = "birth_day", required = false) LocalDate birthDay,
                                                                 @RequestParam(name = "age", required = false) Integer age,
                                                                 @RequestParam(name = "salary", required = false) Double salary,
                                                                 @RequestParam(name = "division", required = false) Division division,
                                                                 @RequestParam (name = "role", required = false) Role role,
                                                                 @RequestParam(name = "direction", required = false) String direction,
                                                                 @RequestParam(name = "sort_param", required = false) Set<String> sortParams) throws EmployeeNotFoundException {
        logger.info("started to handle GET request");
        Employee employee = new Employee(name, lastName, birthDay, age, salary, division, role);
        return ResponseEntity.ok(service.findAllMatchingAndSort(employee, direction, sortParams));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@NotNull @PathVariable Long id, @Valid @RequestBody Employee source) throws EmployeeNotFoundException {
        logger.info("started to handle PUT request");
        return ResponseEntity.ok(service.update(id, source));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteById(@NotNull @PathVariable Long id) {
        logger.info("started to handle DELETE request");
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}