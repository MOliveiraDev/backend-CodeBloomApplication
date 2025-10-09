package com.code.bloom.controller.auth;

import com.code.bloom.dto.login.LoginRequest;
import com.code.bloom.dto.login.LoginResponse;
import com.code.bloom.service.auth.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = loginService.login(request);
        return  ResponseEntity.ok(response);
    }
}
