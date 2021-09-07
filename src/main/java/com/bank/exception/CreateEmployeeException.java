package com.bank.exception;

public class CreateEmployeeException extends Exception{

    private String message;

    public CreateEmployeeException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
