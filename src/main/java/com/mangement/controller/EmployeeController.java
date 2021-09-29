package com.mangement.controller;

import com.mangement.dto.Employee;
import com.mangement.dto.PersonalInformation;
import com.mangement.exception.ProjectNotFoundException;
import com.mangement.service.EmployeeService;
import com.mangement.domain.Division;
import com.mangement.domain.Role;
import com.mangement.exception.EmployeeNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping("/employees")
public class EmployeeController{

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    private static final String GET_EMPLOYEE_BY_ID = "/{employee_id}";
    private static final String GET_ALL_MATCHING_EMPLOYEES = "/";
    private static final String PUT_EMPLOYEE = "/{employee_id}";
    private static final String POST_NEW_EMPLOYEE = "/";
    private static final String DELETE_EMPLOYEE = "/{employee_id}";

    @Autowired
    @Qualifier("employeeServiceImpl")
    private EmployeeService employeeService;

    @PostMapping(POST_NEW_EMPLOYEE)
    public ResponseEntity<Employee> create(@Valid @RequestBody Employee employee) {
        logger.info("started to handle POST request on end point " + POST_NEW_EMPLOYEE);
        employeeService.create(employee);
        return ResponseEntity.created(URI.create(POST_NEW_EMPLOYEE)).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@NotNull @PathVariable Long id) throws EmployeeNotFoundException {
        logger.info("started to handle GET request on end point " + GET_EMPLOYEE_BY_ID);
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @GetMapping(GET_ALL_MATCHING_EMPLOYEES)
    public ResponseEntity<Page<Employee>> findAllMatchingAndSort(Employee employee, Pageable pageable) throws EmployeeNotFoundException {
        logger.info("started to handle GET request on end point " + GET_ALL_MATCHING_EMPLOYEES);
        return ResponseEntity.ok(employeeService.findAllMatchingAndSort(employee, pageable));
    }

    @PutMapping(PUT_EMPLOYEE)
    public ResponseEntity<Employee> update(@NotNull @PathVariable(name = "employee_id") Long id,
                                           @Valid @RequestBody Employee source) throws EmployeeNotFoundException {
        logger.info("started to handle PUT request on end point " + PUT_EMPLOYEE);
        return ResponseEntity.ok(employeeService.update(id, source));
    }

    @DeleteMapping(DELETE_EMPLOYEE)
    public ResponseEntity<Employee> deleteById(@NotNull @PathVariable(name = "employee_id") Long id) {
        logger.info("started to handle DELETE request on end point " + DELETE_EMPLOYEE);
        employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}