package com.mangement.exception;

import lombok.Getter;

@Getter
public class TaskCreationException extends NotFoundException {
    private String message;

    public TaskCreationException(String message) {
        super(message);
        this.message = message;
    }
}
