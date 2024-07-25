package com.lg.electronic_store.exception;

public class BadApiRequest extends RuntimeException{

    public BadApiRequest(String message) {
        super(message);
    }

    public BadApiRequest() {

    }
}
