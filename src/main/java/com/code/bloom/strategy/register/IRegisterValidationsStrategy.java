package com.code.bloom.strategy.register;

import com.code.bloom.dto.register.RegisterRequest;


public interface IRegisterValidationsStrategy {

    void registerResponseValidations(RegisterRequest request);
}
