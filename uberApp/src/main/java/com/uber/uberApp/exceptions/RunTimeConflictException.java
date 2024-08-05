package com.uber.uberApp.exceptions;

public class RunTimeConflictException extends RuntimeException {

    public RunTimeConflictException(String message) {
        super(message);
    }

    public RunTimeConflictException(String message, String email) {
        super("User with email '" + email + "' already exists: " + message);
    }
}