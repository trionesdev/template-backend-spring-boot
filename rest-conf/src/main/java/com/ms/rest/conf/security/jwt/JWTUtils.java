package com.ms.rest.conf.security.jwt;

import io.jsonwebtoken.*;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

public final class JWTUtils {

    public static String generateToken(JWTProperties jwtProperties,String subject, Map<String, Object> claims) {
        Date issueAt = new Date();

        JwtBuilder jwtBuilder = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(issueAt)
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret());
        if(jwtProperties.getExpiration()>0){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(issueAt);
            calendar.add(Calendar.SECOND,jwtProperties.getExpiration());
            jwtBuilder.setExpiration(calendar.getTime());
        }
        if(Objects.nonNull(claims)){
            jwtBuilder.setClaims(claims);
        }
        return jwtBuilder.compact();
    }

    public static Claims parseClaims(JWTProperties jwtProperties, String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(jwtProperties.getSecret()).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            claims = null;
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }
}
