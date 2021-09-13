package com.bank.api.rest.controller.advice.spi;

import com.bank.store.domain.EmployeeEntity;
import com.bank.exception.EmployeeNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

public interface EmployeeControllerAdvice {
    ResponseEntity<EmployeeEntity> badRequest(MethodArgumentNotValidException e);
    ResponseEntity<EmployeeEntity> notFound(EmployeeNotFoundException e);
}
