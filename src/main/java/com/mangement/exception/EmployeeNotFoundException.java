package com.mangement.exception;

import lombok.Getter;

@Getter
public class EmployeeNotFoundException extends NotFoundException {

    private String message;

    public EmployeeNotFoundException(String message) {
        super(message);
        this.message = message;
    }

}
