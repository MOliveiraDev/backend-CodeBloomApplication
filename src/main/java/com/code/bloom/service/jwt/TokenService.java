package com.code.bloom.service.jwt;

import com.code.bloom.database.entity.user.TokenEntity;
import com.code.bloom.database.entity.user.UserEntity;
import com.code.bloom.database.repository.user.TokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;

    public void saveUserToken(String jwt, UserEntity user) {
        TokenEntity token = new TokenEntity();
        token.setToken(jwt);
        token.setLoggedOut(false);
        token.setUser(user);
        tokenRepository.save(token);
    }

    @Transactional
    public void logoutUserTokens(UserEntity user) {
        Objects.requireNonNull(user, "user não pode ser nulo");

        int updated = tokenRepository.logoutAllByUser(user);
        if (updated == 0) {
            List<TokenEntity> ativos = tokenRepository.findAllByUserAndIsLoggedOutFalse(user);
            if (!ativos.isEmpty()) {
                ativos.forEach(t -> t.setLoggedOut(true));
                tokenRepository.saveAll(ativos);
            }
        }
    }

    @Transactional
    public void logoutToken(String jwtToken) {
        Objects.requireNonNull(jwtToken, "jwtToken não pode ser nulo");
        tokenRepository.findByToken(jwtToken).ifPresent(t -> {
            if (!t.isLoggedOut()) {
                t.setLoggedOut(true);
                tokenRepository.save(t);
            }
        });
    }


}
