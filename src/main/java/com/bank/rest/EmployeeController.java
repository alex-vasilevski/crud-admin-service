package com.bank.rest;

import com.bank.domain.Employee;
import com.bank.domain.Role;
import com.bank.service.internal.EmployeeServiceImpl;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
@Validated
public class EmployeeController{

    @Autowired
    @Qualifier("employeeServiceImpl")
    private EmployeeServiceImpl service;

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody @NonNull Employee employee) {
        service.create(employee);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Employee> findById(@PathVariable @NonNull Long id) {
        return ResponseEntity.of(service.findById(id));
    }


    @GetMapping
    public ResponseEntity<Page<Employee>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping
    public ResponseEntity<Page<Employee>> findAllAndSort(@RequestParam(name = "direction") @NonNull String direction,
                                                         @RequestParam(name = "sort_param") @NonNull List<String> sortParams) {

        return ResponseEntity.ok(service.findAllAndSort(direction, sortParams));
    }

    @GetMapping
    public ResponseEntity<Page<Employee>> findAllMatching(@RequestParam(name = "name") String name,
                                                          @RequestParam(name = "last_name") String lastName,
                                                          @RequestParam(name = "birth_day") LocalDate birthDay,
                                                          @RequestParam(name = "age") Integer age,
                                                          @RequestParam(name = "salary") Double salary,
                                                          @RequestParam (name = "role") Role role) {
        return ResponseEntity.ok(service.findAllMatching(new Employee(name, lastName, birthDay, age, salary, role)));
    }

    @GetMapping
    public ResponseEntity<Page<Employee>> findAllMatchingAndSort(@RequestParam(name = "name") String name,
                                                                 @RequestParam(name = "last_name") String lastName,
                                                                 @RequestParam(name = "birth_day") LocalDate birthDay,
                                                                 @RequestParam(name = "age") Integer age,
                                                                 @RequestParam(name = "salary") Double salary,
                                                                 @RequestParam (name = "role") Role role,
                                                                 @RequestParam(name = "direction") @NotNull String direction,
                                                                 @RequestParam(name = "sort_param") @NotNull List<String> sortParams) {
        return ResponseEntity.ok(service.findAllMatchingAndSort(new Employee(name, lastName, birthDay, age, salary, role), direction, sortParams));
    }

    @PutMapping("{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id, @RequestBody Employee source) {
        return ResponseEntity.of(service.update(id, source));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Employee> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}