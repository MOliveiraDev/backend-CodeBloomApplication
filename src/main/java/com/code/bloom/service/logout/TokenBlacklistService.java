package com.code.bloom.service.logout;

import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TokenBlacklistService {

    private static final Set<String> blacklistedTokens = Set.of();

    public void blacklistToken(String token) {
        blacklistedTokens.add(token);
    }

    public boolean isTokenBlacklisted(String token) {
        return blacklistedTokens.contains(token);
    }

    public void clearBlacklist() {
        blacklistedTokens.clear();
    }
}
