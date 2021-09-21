package com.mangement.exception;

import lombok.Getter;

@Getter
public class TaskNotFoundException extends Exception{

    private String message;

    public TaskNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
