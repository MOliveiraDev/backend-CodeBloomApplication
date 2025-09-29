package com.code.bloom.exceptions.register;

public class PasswordEqualsException extends RuntimeException {

    public PasswordEqualsException(String message) {
        super(message);
    }
}
