package com.code.bloom.strategy.login;

import com.code.bloom.dto.login.LoginRequest;

public interface ILoginValidations {

    void loginResponseValidation(LoginRequest loginRequest);
}
