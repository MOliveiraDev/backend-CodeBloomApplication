package com.code.bloom.exceptions.register;

public class UsernameActuallyExistsException extends RuntimeException {

    public UsernameActuallyExistsException(String username) {
        super("O nome de usuário " + username + " já está cadastrado.");
    }
}
