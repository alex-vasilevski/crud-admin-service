package com.mangement.exception;

import lombok.Getter;

@Getter
public class TaskCreationException extends Exception {
    private String message;

    public TaskCreationException(String message) {
        super(message);
        this.message = message;
    }
}
