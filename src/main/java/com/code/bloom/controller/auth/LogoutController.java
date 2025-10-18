package com.code.bloom.controller.auth;

import com.code.bloom.service.auth.LogoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/logout")
@RequiredArgsConstructor
public class LogoutController {

    private final LogoutService logoutService;

    @PostMapping
    public ResponseEntity<String> logout(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        String response = logoutService.logout(authorizationHeader);
        return ResponseEntity.ok(response);
    }
}
