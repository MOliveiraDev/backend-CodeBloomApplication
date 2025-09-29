package com.code.bloom.strategy.register.impl;

import com.code.bloom.dto.register.RegisterRequest;
import com.code.bloom.exceptions.register.PasswordEqualsException;
import com.code.bloom.strategy.register.IRegisterValidations;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class PasswordEqualsStrategy implements IRegisterValidations {

    @Override
    @SneakyThrows
    public void registerResponseValidations(RegisterRequest request) {

        if (!request.confirmPassword().equals(request.password())) {
            throw new PasswordEqualsException("Senha inváida");
        }
    }
}
