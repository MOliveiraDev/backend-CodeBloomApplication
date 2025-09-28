package com.code.bloom.strategy.register;

import com.code.bloom.dto.register.RegisterRequest;


public interface IRegisterValidations {

    void registerResponseValidations(RegisterRequest request);
}
