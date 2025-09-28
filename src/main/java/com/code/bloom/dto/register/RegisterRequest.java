package com.code.bloom.dto.register;

public record RegisterRequest(String username, String phoneNumber, String email, String password, String confirmPassword) {
}
