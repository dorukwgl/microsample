package com.doruk.domain.exception;

public class DomainException extends RuntimeException {
    private final int statusCode;

    DomainException(int statusCode, String message) {
        this.statusCode = statusCode;
        super(message);
    }

    public int getStatusCode() {
        return statusCode;
    }
}
