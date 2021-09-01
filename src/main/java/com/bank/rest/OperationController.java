package com.bank.rest;

import com.bank.dto.Administrator;
import com.bank.dto.OperationMetaAggregator;
import com.bank.operation.Operation;
import com.bank.policy.operation.OperationStatus;
import com.bank.service.OperationFactoryService;
import com.bank.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/operation")
public class OperationController {

    @Autowired
    private OperationService operationService;

    @PostMapping
    public ResponseEntity<OperationStatus> perform(@RequestBody OperationMetaAggregator operationDetails) {
        return ResponseEntity.ok(operationService.perform(operationDetails));
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Administrator> findById(@PathVariable Long id) {
        return ResponseEntity.of(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Administrator>> findAll() {
        return ResponseEntity.of(Optional.of(service.findAll()));
    }

}
