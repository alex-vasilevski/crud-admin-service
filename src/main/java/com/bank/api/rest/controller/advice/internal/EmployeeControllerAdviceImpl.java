package com.bank.api.rest.controller.advice.internal;

import com.bank.api.rest.controller.EmployeeController;
import com.bank.api.rest.controller.advice.spi.EmployeeControllerAdvice;
import com.bank.store.domain.EmployeeEntity;
import com.bank.exception.EmployeeNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(assignableTypes = EmployeeController.class)
public class EmployeeControllerAdviceImpl implements EmployeeControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeControllerAdviceImpl.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @Override
    public ResponseEntity<EmployeeEntity> badRequest(MethodArgumentNotValidException e){
        logger.info("exception "+ e.getClass().getSimpleName() +" occurred; message: " + e.getMessage());
        return ResponseEntity.badRequest().build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmployeeNotFoundException.class)
    @Override
    public ResponseEntity<EmployeeEntity> notFound(EmployeeNotFoundException e){
        logger.info("exception "+ e.getClass().getSimpleName() +" occurred; message: " + e.getMessage());
        return ResponseEntity.notFound().build();
    }
}
