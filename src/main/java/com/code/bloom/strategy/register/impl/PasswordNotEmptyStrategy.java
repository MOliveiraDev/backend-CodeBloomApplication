package com.code.bloom.strategy.register.impl;

import com.code.bloom.dto.register.RegisterRequest;
import com.code.bloom.strategy.register.IRegisterValidations;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class PasswordNotEmptyStrategy implements IRegisterValidations {

    @Override
    @SneakyThrows
    public void registerResponseValidations(RegisterRequest request) {

        if (request.password() == null || request.password().isEmpty()) {
            throw new IllegalArgumentException("A senha não pode estar em branco");
        }

        if (request.confirmPassword() == null || request.confirmPassword().isEmpty()) {
            throw new IllegalArgumentException("A confirmação de senha não pode estar em branco");
        }
    }
}
