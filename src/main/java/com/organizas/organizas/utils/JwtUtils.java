package com.organizas.organizas.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.organizas.organizas.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Component
public class JwtUtils {

    private final Algorithm algorithm;
    private final long expirationMs;

    public JwtUtils(
            @Value("${spring.auth.secret_key}") String secretKey,
            @Value("${spring.auth.expiration_date}") long expirationMs
    ) {
        this.algorithm = Algorithm.HMAC256(secretKey);
        this.expirationMs = expirationMs;
    }

    public String generateToken(UserDetailsImpl user) {
        Instant now = Instant.now();
        Instant expiresAt = now.plusMillis(expirationMs);

        return JWT.create()
                .withSubject(user.getId().toString())
                .withIssuedAt(now)
                .withExpiresAt(expiresAt)
                .sign(algorithm);
    }

    public Optional<UUID> validateToken(String token) {
        try {
            return Optional.of(
                    UUID.fromString(
                            JWT.require(algorithm)
                                    .build()
                                    .verify(token)
                                    .getSubject()
                    )
            );
        } catch (JWTVerificationException | IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
