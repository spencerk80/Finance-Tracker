package com.GenSpark.Finance.Tracker.util;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JWT {

    private final String secretKey;

    public JWT(@Value("${jwt.key}") String key) {
        this.secretKey = key;
    }

    public String createJWT(UserDetails userDetails) {
        LocalDateTime   iat         = LocalDateTime.now();
        LocalDateTime   exp         = iat.plusDays(1);
        byte[]          keyBytes    = DatatypeConverter.parseBase64Binary(secretKey);
        Key             signingKey  = new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());

        JwtBuilder jwtBuilder = Jwts.builder()
                .setIssuedAt(Date.from(iat.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(exp.atZone(ZoneId.systemDefault()).toInstant()))
                .claim("email", userDetails.getUsername())
                .claim("role", userDetails.getAuthorities().toString())
                .signWith(signingKey);

        return jwtBuilder.compact();
    }

    public String getUserEmail(String jwt) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        return (String) claims.get("email");
    }

    public String getUserRole(String jwt) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        return (String) claims.get("role");
    }

    public LocalDateTime getJwtExpiration(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                .build()
                .parseClaimsJws(jwt)
                .getBody()
                .getExpiration()
                .toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public boolean validateJwt(String jwt, UserDetails userDetails) {
        Claims claims;

        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch(Exception e) { return false; }

        if( ! (claims.get("email")).equals(userDetails.getUsername()))                 return false;
        if( ! (claims.get("role")).equals(userDetails.getAuthorities().toString()))    return false;

        return ! claims.getExpiration().before(new Date());
    }
}