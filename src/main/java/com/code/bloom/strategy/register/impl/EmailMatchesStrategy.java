package com.code.bloom.strategy.register.impl;

import com.code.bloom.dto.register.RegisterRequest;
import com.code.bloom.exceptions.register.EmailEmptyException;
import com.code.bloom.strategy.register.IRegisterValidationsStrategy;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class EmailMatchesStrategy implements IRegisterValidationsStrategy {

    @SneakyThrows
    @Override
    public void registerResponseValidations(RegisterRequest request) {

        if (!request.email().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new EmailEmptyException("Forneça um email válido");
        }

    }
}
