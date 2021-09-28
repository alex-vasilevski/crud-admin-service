package com.mangement.exception;

import lombok.Getter;

@Getter
public class TaskNotFoundException extends NotFoundException{

    private String message;

    public TaskNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
