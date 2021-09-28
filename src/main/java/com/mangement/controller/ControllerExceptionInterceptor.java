package com.mangement.controller;

import com.mangement.exception.EmployeeNotFoundException;
import com.mangement.exception.NotFoundException;
import com.mangement.exception.ProjectNotFoundException;
import com.mangement.exception.TaskNotFoundException;
import com.mangement.domain.EmployeeEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(assignableTypes = {ProjectController.class, EmployeeController.class})
public class ControllerExceptionInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionInterceptor.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<EmployeeEntity> badRequest(MethodArgumentNotValidException e){
        logger.info("exception "+ e.getClass().getSimpleName() +" occurred; message: " + e.getMessage());
        return ResponseEntity.badRequest().build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ProjectNotFoundException.class, TaskNotFoundException.class, EmployeeNotFoundException.class})
    public ResponseEntity<EmployeeEntity> notFound(NotFoundException e){
        logger.info("exception "+ e.getClass().getSimpleName() +" occurred; message: " + e.getMessage());
        return ResponseEntity.notFound().build();
    }

}
