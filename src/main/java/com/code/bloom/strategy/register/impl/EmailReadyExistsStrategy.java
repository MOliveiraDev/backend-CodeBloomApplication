package com.code.bloom.strategy.register.impl;

import com.code.bloom.database.repository.user.UserRepository;
import com.code.bloom.dto.register.RegisterRequest;
import com.code.bloom.strategy.register.IRegisterValidations;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailReadyExistsStrategy implements IRegisterValidations {

    private final UserRepository userRepository;

    @SneakyThrows
    @Override
    public void registerResponseValidations(RegisterRequest registerRequest) {

        var user = userRepository.findByEmail(registerRequest.email());

        if (!registerRequest.email().equals(user.getEmail())) {
            throw new IllegalArgumentException("Email já está cadastrado");}
    }
}
