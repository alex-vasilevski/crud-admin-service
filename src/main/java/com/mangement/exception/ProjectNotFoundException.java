package com.mangement.exception;

public class ProjectNotFoundException extends Exception {
    private String message;

    public ProjectNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
