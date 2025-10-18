package com.code.bloom.strategy.register.impl;

import com.code.bloom.dto.register.RegisterRequest;
import com.code.bloom.exceptions.register.EmailEmptyException;
import com.code.bloom.strategy.register.IRegisterValidationsStrategy;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class EmailNotEmptyStrategy implements IRegisterValidationsStrategy {

    @Override
    @SneakyThrows
    public void registerResponseValidations(RegisterRequest request) {
        if (request.email() == null || request.email().isEmpty()) {
            throw new EmailEmptyException("O email não pode estar em branco");
        }
    }
}
