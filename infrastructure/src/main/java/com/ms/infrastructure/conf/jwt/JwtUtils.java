package com.ms.infrastructure.conf.jwt;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.SneakyThrows;
import org.apache.commons.collections4.MapUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public final class JwtUtils {

    @SneakyThrows
    public static String generateToken(JwtProperties jwtProperties, String subject, Map<String, Object> claims) {
        Date issueAt = new Date();

        JWTClaimsSet.Builder jwtClaimsSetBuilder = new JWTClaimsSet.Builder()
                .subject(subject)
                .issueTime(issueAt);
        if(MapUtils.isNotEmpty(claims)){
            claims.forEach(jwtClaimsSetBuilder::claim);
        }
        if (jwtProperties.getExpiration() > 0) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(issueAt);
            calendar.add(Calendar.SECOND, jwtProperties.getExpiration());
            jwtClaimsSetBuilder.expirationTime(calendar.getTime());
        }
        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), jwtClaimsSetBuilder.build());
        signedJWT.sign(new MACSigner(jwtProperties.getSecret()));
        return signedJWT.serialize();
    }

    @SneakyThrows
    public static Map<String,Object> parseClaims(JwtProperties jwtProperties, String token) {
        JWSVerifier verifier = new MACVerifier(jwtProperties.getSecret());
        SignedJWT signedJWT = SignedJWT.parse(token);
        if(!signedJWT.verify(verifier)){
            return null;
        }
        return signedJWT.getJWTClaimsSet().getClaims();
    }
}
