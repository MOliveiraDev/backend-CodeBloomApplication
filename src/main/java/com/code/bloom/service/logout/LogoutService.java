package com.code.bloom.service.logout;

import com.code.bloom.database.entity.user.UserEntity;
import com.code.bloom.database.entity.user.UserStatus;
import com.code.bloom.database.repository.user.UserRepository;
import com.code.bloom.service.jwt.JwtService;
import com.code.bloom.service.jwt.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LogoutService {

    private final UserRepository userRepository;
    private final TokenBlacklistService tokenBlacklistService;
    private final JwtService jwtService;
    private final TokenService tokenService;


    @Transactional
    public String logout(String authorizationHeaderOrToken) {
        try {
            if (authorizationHeaderOrToken == null || authorizationHeaderOrToken.isBlank()) {
                return "Token ausente. Logout não realizado.";
            }

            String jwt = normalizeToken(authorizationHeaderOrToken);
            if (jwt.isBlank()) {
                return "Token ausente. Logout não realizado.";
            }


            if (tokenBlacklistService.isTokenBlacklisted(jwt)) {
                log.info("Logout idempotente: token já invalidado");
                return "Logout realizado com sucesso";
            }

            String username = jwtService.extractUsername(jwt);

            UserEntity user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));


            tokenService.logoutToken(jwt);


            user.setUserStatus(UserStatus.OFFLINE);
            userRepository.save(user);


            tokenBlacklistService.blacklistToken(jwt);

            log.info("Logout realizado para o usuário: {}", username);
            return "Logout realizado com sucesso";
        } catch (Exception e) {
            log.error("Erro ao realizar logout: {}", e.getMessage());
            return "Falha ao realizar logout: token inválido ou expirado.";
        }
    }

    private String normalizeToken(String headerOrToken) {
        String raw = headerOrToken.trim();
        if (raw.regionMatches(true, 0, "Bearer ", 0, 7)) {
            return raw.substring(7).trim();
        }
        return raw;
    }
}