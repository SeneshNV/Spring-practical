package com.augusta.jwt.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Service
public class JWTService {
    private final SecretKey secretKey;

    public JWTService() {
        try {
            SecretKey k = KeyGenerator.getInstance("HMACSHA256").generateKey();
//            secretKey = Keys.hmacShaKeyFor(k.getEncoded());
            secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String getJWTToken(){
        return Jwts
                .builder()
                .subject("Senesh")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*15))
                .signWith(secretKey )
                .compact();
    }

    public String getUsername(String token){
        return Jwts
                .parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject(); // for get subject
    }
}
