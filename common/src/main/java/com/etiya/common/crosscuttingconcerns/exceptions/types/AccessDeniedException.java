package com.etiya.common.crosscuttingconcerns.exceptions.types;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException(String message) {
        super(message);
    }
}
