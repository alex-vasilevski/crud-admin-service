package com.mangement.api.rest.controller.project.advice;

import com.mangement.api.rest.controller.project.ProjectController;
import com.mangement.exception.ProjectNotFoundException;
import com.mangement.store.domain.employee.EmployeeEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(assignableTypes = ProjectController.class)
public class ProjectControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ProjectControllerAdvice.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<EmployeeEntity> badRequest(MethodArgumentNotValidException e){
        logger.info("exception "+ e.getClass().getSimpleName() +" occurred; message: " + e.getMessage());
        return ResponseEntity.badRequest().build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<EmployeeEntity> notFound(ProjectNotFoundException e){
        logger.info("exception "+ e.getClass().getSimpleName() +" occurred; message: " + e.getMessage());
        return ResponseEntity.notFound().build();
    }
}
