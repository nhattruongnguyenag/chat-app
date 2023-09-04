package com.chatapp.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.function.Function;

@Component
public class TokenProvider implements Serializable {
    public static final int TOKEN_VALIDITY_IN_MILISECONDS = 3_600_000;
    public static final String JWT_SIGNING_KEY = "com.template";

    public String generateToken(Authentication authentication) {
        return Jwts.builder()
                .setSubject(authentication.getName())
                .signWith(SignatureAlgorithm.HS256, JWT_SIGNING_KEY)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY_IN_MILISECONDS))
                .compact();
    }

    public String extractUsernameFromToken(String token) {
        return getSpecifiedClaim(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getSpecifiedClaim(token, Claims::getExpiration);
    }

    public Boolean isTokenNotExpired(String token) {
        final Date currentTime = new Date();
        final Date expiration = getExpirationDateFromToken(token);
        return currentTime.before(expiration);
    }

    public <T> T getSpecifiedClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(JWT_SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
