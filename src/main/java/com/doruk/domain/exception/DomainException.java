package com.doruk.domain.exception;

import lombok.Getter;

@Getter
public class DomainException extends RuntimeException {
    private final int statusCode;

    public DomainException(int statusCode, String message) {
        this.statusCode = statusCode;
        super(message);
    }
}
