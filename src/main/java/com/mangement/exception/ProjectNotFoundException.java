package com.mangement.exception;

import lombok.Getter;

@Getter
public class ProjectNotFoundException extends NotFoundException {

    private String message;

    public ProjectNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
