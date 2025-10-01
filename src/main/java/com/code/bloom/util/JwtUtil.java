package com.code.bloom.util;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUtil {

    private final JwtDecoder jwtDecoder;

    public String extractEmail(String token) {
        Jwt jwt = jwtDecoder.decode(token);
        return jwt.getClaimAsString("email");
    }
}
