package com.bank.exception;

public class EmployeeNotFoundException extends Exception{

    private String message;

    public EmployeeNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
