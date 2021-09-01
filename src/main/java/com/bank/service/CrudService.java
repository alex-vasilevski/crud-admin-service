package com.bank.service;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CrudService<Dto, Id>{
    void create(Dto dto);
    Optional<Dto> findById(Id id);
    Iterable<Dto> findAll();
    Optional<Dto> update(Id id, Dto sourceDto);
    void deleteById(Id id);
}
