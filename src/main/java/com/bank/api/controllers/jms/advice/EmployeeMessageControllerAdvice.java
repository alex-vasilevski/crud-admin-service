package com.bank.api.controllers.jms.advice;

import com.bank.api.controllers.jms.EmployeeMessageController;
import com.bank.api.controllers.rest.advice.EmployeeControllerAdviceImpl;
import com.bank.exception.EmployeeNotFoundException;
import com.bank.store.domain.EmployeeEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(assignableTypes = EmployeeMessageController.class)
public class EmployeeMessageControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeMessageControllerAdvice.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<EmployeeEntity> badRequest(MethodArgumentNotValidException e){
        logger.info("exception "+ e.getClass().getSimpleName() +" occurred; message: " + e.getMessage());
        return ResponseEntity.badRequest().build();
    }


    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<EmployeeEntity> notFound(EmployeeNotFoundException e){
        logger.info("exception "+ e.getClass().getSimpleName() +" occurred; message: " + e.getMessage());
        return ResponseEntity.notFound().build();
    }
}
