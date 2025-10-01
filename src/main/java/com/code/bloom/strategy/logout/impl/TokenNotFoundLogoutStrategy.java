package com.code.bloom.strategy.logout.impl;

import com.code.bloom.strategy.logout.ILogoutValidations;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class TokenNotFoundLogoutStrategy implements ILogoutValidations {

    @SneakyThrows
    @Override
    public void logoutResponseValidation(String token) {

        if (token == null || token.isEmpty()) {
            throw new IllegalArgumentException("Token não encontrado");
        }
    }
}
