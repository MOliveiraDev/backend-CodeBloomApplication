package com.code.bloom.strategy.auth.impl;

import com.code.bloom.database.entity.user.UserEntity;
import com.code.bloom.database.repository.user.UserRepository;
import com.code.bloom.exceptions.auth.UnauthorizedException;
import com.code.bloom.strategy.auth.IAuthenticationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityContextAuthenticationStrategy implements IAuthenticationStrategy{

    private final UserRepository userRepository;

    @Override
    public UserEntity getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())) {
            throw new UnauthorizedException("Acesso negado. Usuário não autenticado.");
        }

        String username = auth.getName();

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UnauthorizedException("Usuário não encontrado: " + username));
    }
}
