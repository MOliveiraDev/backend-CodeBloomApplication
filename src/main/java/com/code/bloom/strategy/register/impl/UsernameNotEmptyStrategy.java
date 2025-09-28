package com.code.bloom.strategy.register.impl;

import com.code.bloom.dto.register.RegisterRequest;
import com.code.bloom.strategy.register.IRegisterValidations;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class UsernameNotEmptyStrategy implements IRegisterValidations {

    @Override
    @SneakyThrows
    public void registerResponseValidations(RegisterRequest request) {

        if (request.username() == null || request.username().isEmpty()) {
            throw new IllegalArgumentException("Preencha o nome do seu usuário");
        }
    }
}
