package com.code.bloom.config.security;

import com.code.bloom.service.jwt.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestartTokens {

    private final TokenService tokenService;

    @Scheduled(fixedRate = 18000)
    public void cleanLoggedOutTokens() {
        tokenService.deleteLoggedOutTokens();
    }
}
