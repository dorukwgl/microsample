package com.doruk.domain.exception;

public class NoSeatsAvailableException extends DomainException {
    public NoSeatsAvailableException(String msg) {
        super(msg);
    }
}
