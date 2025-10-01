package com.code.bloom.strategy.login.impl;

import com.code.bloom.database.repository.user.UserRepository;
import com.code.bloom.dto.login.LoginRequest;
import com.code.bloom.exceptions.login.PasswordIncorretException;
import com.code.bloom.strategy.login.ILoginValidations;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordIncorretStrategy implements ILoginValidations {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    @SneakyThrows
    @Override
    public void loginResponseValidation(LoginRequest loginRequest) {

        var user = userRepository.findByEmail(loginRequest.email());

        if (!encoder.matches(loginRequest.password(), user.getPassword())) {
            throw new PasswordIncorretException("Senha incorreta");
        }
    }
}
