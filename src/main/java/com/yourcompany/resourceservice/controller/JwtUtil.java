package com.yourcompany.resourceservice.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureException;

public class JwtUtil {
    private static final String SECRET_KEY = "your-secret-key"; // same as Auth service

    public static Claims validateToken(String token) throws SignatureException {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
