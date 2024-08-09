package com.flow.main.service.security;

import com.flow.main.common.property.JwtProperty;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class JwtUtil {

    private final SecretKey secretKey;
    private final JwtProperty jwtProperty;

    public JwtUtil(JwtProperty jwtProperty) {
        this.jwtProperty = jwtProperty;
        secretKey = new SecretKeySpec(jwtProperty.getSecret().getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }
    public void checkValidToken(String token){
        if (token.isEmpty() || !token.startsWith("Bearer ")){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token is empty or no Bearer");
        }
    }

    public String getToken(String authHeader){
        return authHeader.split(" ")[1];
    }

    public String getEmail(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload()
            .get("email", String.class);
    }

    public String getRole(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload()
            .get("role", String.class);
    }

    public Long getUserId(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload()
            .get("user_id", Long.class);
    }

    public void isExpired(String token) {
        if(Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload()
            .getExpiration().before(new Date())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token is expired");
        }
    }

    public String createAccessToken(Long userId, String email, String role) {
        return Jwts.builder()
            .claim("user_id", userId)
            .claim("email", email)
            .claim("role", role)
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + jwtProperty.getAccess().getExpiration()))
            .signWith(secretKey)
            .compact();
    }

    public String createRefreshToken(Long userId, String email, String role){
        return Jwts.builder()
            .claim("user_id", userId)
            .claim("email", email)
            .claim("role", role)
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + jwtProperty.getRefresh().getExpiration()))
            .signWith(secretKey)
            .compact();
    }
}

