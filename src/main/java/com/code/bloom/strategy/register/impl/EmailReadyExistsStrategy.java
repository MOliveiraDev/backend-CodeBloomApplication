package com.code.bloom.strategy.register.impl;

import com.code.bloom.database.repository.user.UserRepository;
import com.code.bloom.dto.register.RegisterRequest;
import com.code.bloom.exceptions.register.EmailActuallyExistsException;
import com.code.bloom.strategy.register.IRegisterValidationsStrategy;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailReadyExistsStrategy implements IRegisterValidationsStrategy {

    private final UserRepository userRepository;

    @SneakyThrows
    @Override
    public void registerResponseValidations(RegisterRequest registerRequest) {

        var user = userRepository.findByEmail(registerRequest.email());

        if (user.isPresent()) {
            throw new EmailActuallyExistsException("Email já cadastrado");}
    }
}
