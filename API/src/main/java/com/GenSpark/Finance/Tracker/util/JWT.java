package com.GenSpark.Finance.Tracker.util;

import io.jsonwebtoken.*;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JWT {
    //Normally this would be read from some source and not hard coded
    private static final String secretKey = "Kg8808o$cgLEKPxsUjrSNjiAkKt9oMq7Kg8808o$cgLEKPxsUjrSNjiAkKt9oMq7";
    private static final List<String> blacklist = new ArrayList<>();

    public static String createJWT(UserDetails userDetails) {
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

    public static String getUserEmail(String jwt) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        return (String) claims.get("email");
    }

    public static String getUserRole(String jwt) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        return (String) claims.get("role");
    }

    public static LocalDateTime getJwtExpiration(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                .build()
                .parseClaimsJws(jwt)
                .getBody()
                .getExpiration()
                .toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static boolean validateJwt(String jwt, UserDetails userDetails) {
        Claims claims;

        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch(Exception e) { return false; }

        if(jwtIsBlacklisted(jwt))                                                      return false;
        if( ! (claims.get("email")).equals(userDetails.getUsername()))                 return false;
        if( ! (claims.get("role")).equals(userDetails.getAuthorities().toString()))    return false;

        if( ! claims.getExpiration().before(new Date())) System.out.println("date checks out");

        return ! claims.getExpiration().before(new Date());
    }

    public static boolean jwtIsBlacklisted(String jwt) {
        return blacklist.contains(jwt);
    }

    public static void blacklistJwt(String jwt) {
        blacklist.add(jwt);

        //Clean up the blacklist on a separate thread
        new Thread(() -> {
            LocalDateTime now = LocalDateTime.now();

            blacklist.forEach(token -> {
                if(JWT.getJwtExpiration(token).isBefore(now))
                    blacklist.remove(token);
            });
        });
    }
}