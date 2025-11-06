package com.code.bloom.service.jwt;

import com.code.bloom.database.entity.auth.TokenEntity;
import com.code.bloom.database.entity.user.UserEntity;
import com.code.bloom.database.entity.user.UserStatus;
import com.code.bloom.database.repository.auth.TokenRepository;
import com.code.bloom.database.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.nimbusds.jwt.SignedJWT;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;

    public void saveUserToken(String jwt, UserEntity user) {
        TokenEntity token = new TokenEntity();
        token.setToken(jwt);
        token.setLoggedOut(false);
        token.setUser(user);
        tokenRepository.save(token);
    }

    @Transactional
    public void logoutToken(String jwtToken) {
        Objects.requireNonNull(jwtToken, "jwtToken não pode ser nulo");

        if (isTokenExpired(jwtToken)) {
            tokenRepository.findByToken(jwtToken).ifPresent(t -> markTokenLoggedOutAndMaybeOffline(t));
            return;
        }

        tokenRepository.findByToken(jwtToken).ifPresent(t -> markTokenLoggedOutAndMaybeOffline(t));

        tokenRepository.findByToken(jwtToken).ifPresent(t -> {
            t.setLoggedOut(true);
            tokenRepository.save(t);
        });
    }

    private void markTokenLoggedOutAndMaybeOffline(TokenEntity t) {
        if (t == null) return;
        if (t.isLoggedOut()) return;

        t.setLoggedOut(true);
        tokenRepository.save(t);

        UserEntity user = t.getUser();
        if (user != null) {
            long activeTokens = tokenRepository.countByUserAndIsLoggedOutFalse(user);
            if (activeTokens == 0) {
                if (user.getUserStatus() != UserStatus.OFFLINE) {
                    user.setUserStatus(UserStatus.OFFLINE);
                    userRepository.save(user);
                }
            }
        }
    }

    public boolean isTokenExpired(String jwtToken) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(jwtToken);
            Date exp = signedJWT.getJWTClaimsSet().getExpirationTime();
            if (exp == null) return true;
            return exp.before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

    @Transactional
    public void markExpiredTokens() {
        List<TokenEntity> tokens = tokenRepository.findAllByIsLoggedOutFalse();
        for (TokenEntity t : tokens) {
            if (t == null || t.getToken() == null) continue;
            if (isTokenExpired(t.getToken())) {
                markTokenLoggedOutAndMaybeOffline(t);
            }
        }
    }

    public void deleteLoggedOutTokens() {
        List<TokenEntity> tokens = tokenRepository.findAllByIsLoggedOutTrue();

        if (!tokens.isEmpty()) {
            tokenRepository.deleteAll(tokens);
            System.out.println("Tokens deslogados removidos: " + tokens.size());
        }
    }
}
