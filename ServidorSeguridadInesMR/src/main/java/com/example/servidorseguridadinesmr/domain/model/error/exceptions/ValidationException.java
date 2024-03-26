package com.example.servidorseguridadinesmr.domain.model.error.exceptions;

public class ValidationException extends RuntimeException{
    public ValidationException(String message) {
        super(message);
    }
}
