package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {
    private final String secret;
    private final long validityInMs;

    public JwtTokenProvider(String secret, long validityInMs) {
        this.secret = secret;
        this.validityInMs = validityInMs;
    }

    public String generateToken(String email, String role, Long userId) {
        return "eyJhbGciOiJIUzI1NiJ9.mockToken." + email;
    }

    public boolean validateToken(String token) {
        return token != null && token.startsWith("eyJhbGci");
    }
}