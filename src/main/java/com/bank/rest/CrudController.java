package com.bank.rest;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CrudController<Dto, Id>{

    ResponseEntity<Dto> create(Dto dto);
    ResponseEntity<Dto> findById(Id id);
    ResponseEntity<List<Dto>> findAll();
    ResponseEntity<Dto> update(Id id, Dto source);
    ResponseEntity<Dto> deleteById(Id id);

}
