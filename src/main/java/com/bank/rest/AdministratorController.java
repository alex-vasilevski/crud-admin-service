package com.bank.rest;

import com.bank.domain.Administrator;
import com.bank.service.crud.internal.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/administrator")
public class AdministratorController implements CrudController<Administrator, Long> {

    @Autowired
    private AdministratorService service;

    @PostMapping
    public ResponseEntity<Administrator> create(@RequestBody Administrator administrator) {
        service.create(administrator);
        return ResponseEntity.ok().build();
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Administrator> findById(@PathVariable Long id) {
        return ResponseEntity.of(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Administrator>> findAll() {
        return ResponseEntity.of(Optional.of(service.findAll()));
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Administrator> update(@PathVariable Long id, @RequestBody Administrator source) {
        return ResponseEntity.of(service.update(id, source));
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Administrator> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}