package com.bank.rest;

import com.bank.domain.Employee;
import com.bank.service.crud.internal.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admins")
public class EmployeeController implements CrudController<Employee, Long> {

    @Autowired
    private EmployeeService service;

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        service.create(employee);
        return ResponseEntity.ok().build();
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Employee> findById(@PathVariable Long id) {
        return ResponseEntity.of(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Employee>> findAll() {
        return ResponseEntity.of(Optional.of(service.findAll()));
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Employee> update(@PathVariable Long id, @RequestBody Employee source) {
        return ResponseEntity.of(service.update(id, source));
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Employee> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}