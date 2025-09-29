package com.code.bloom.exceptions.register;

public class UsernameIsEmptyException extends RuntimeException {

    public UsernameIsEmptyException(String message) {
        super(message);
    }
}
