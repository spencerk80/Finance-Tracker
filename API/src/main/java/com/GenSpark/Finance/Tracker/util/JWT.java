package com.GenSpark.Finance.Tracker.util;

import io.jsonwebtoken.*;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class JWT {
    //Normally this would be read from some source and not hard coded
    private static final String secretKey = "Kg8808o$cgLEKPxsUjrSNjiAkKt9oMq7Kg8808o$cgLEKPxsUjrSNjiAkKt9oMq7";

    public static String createJWT(UserDetails userDetails) {
        LocalDate   now         = LocalDate.now();
        Date        iat         = Date.from(now.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date        exp         = Date.from(now.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        byte[]      keyBytes    = DatatypeConverter.parseBase64Binary(secretKey);
        Key         signingKey  = new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());

        JwtBuilder jwtBuilder = Jwts.builder()
                .setIssuedAt(iat)
                .setExpiration(exp)
                .claim("username", userDetails.getUsername())
                .claim("role", userDetails.getAuthorities())
                .signWith(signingKey);

        return jwtBuilder.compact();
    }

    public static Claims validateJwt(String jwt) throws ExpiredJwtException, UnsupportedJwtException,
            MalformedJwtException {

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        return claims;
    }
}