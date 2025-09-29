package com.code.bloom.strategy.register.impl;

import com.code.bloom.dto.register.RegisterRequest;
import com.code.bloom.exceptions.register.PasswordLengthExeption;
import com.code.bloom.strategy.register.IRegisterValidations;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class PasswordLengthStrategy implements IRegisterValidations {

    @Override
    @SneakyThrows
    public void registerResponseValidations(RegisterRequest request) {

        if (request.password().length() < 8) {
            throw new PasswordLengthExeption("A senha deve ter no mínimo 8 caracteres");
        }
    }
}
