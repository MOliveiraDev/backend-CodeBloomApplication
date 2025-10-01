package com.code.bloom.controller.login;

import com.code.bloom.dto.login.LoginRequest;
import com.code.bloom.dto.login.LoginResponse;
import com.code.bloom.service.login.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequestMapping("/api/v1/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public LoginResponse loginUser(@RequestBody LoginRequest request) {
        return loginService.login(request);
    }
}
