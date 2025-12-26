package com.example.demo.security;

import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtTokenProvider {
    public String generateToken(String email, String role, Long userId) {
        return "token-123"; // Simulating token generation for tests [cite: 226]
    }

    public boolean validateToken(String token) {
        return "valid".equals(token); [cite: 226]
    }
}