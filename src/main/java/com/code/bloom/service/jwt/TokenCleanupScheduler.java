package com.code.bloom.service.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenCleanupScheduler {

    private final TokenService tokenService;

    @Scheduled(fixedDelayString = "${token.cleanup.fixed-delay-ms:300000}", initialDelayString = "${token.cleanup.initial-delay-ms:60000}")
    public void cleanupExpiredTokens() {
        try {
            tokenService.markExpiredTokens();
            tokenService.deleteLoggedOutTokens();
        } catch (Exception ex) {
            System.err.println("Erro ao executar limpeza de tokens: " + ex.getMessage());
        }
    }
}

