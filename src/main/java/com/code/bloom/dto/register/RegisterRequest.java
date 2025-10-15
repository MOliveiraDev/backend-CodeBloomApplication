package com.code.bloom.dto.register;

import java.util.Date;

public record RegisterRequest(String username, String phoneNumber, Date birthdayDate, String email, String password) {
}
