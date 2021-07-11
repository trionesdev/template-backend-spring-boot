package com.ms.rest.conf.security.jwt;


import com.google.common.collect.Maps;
import com.ms.core.commons.ctx.OperatorRoleEnum;
import io.jsonwebtoken.Claims;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

import static com.ms.rest.conf.security.jwt.ClaimsKeyConstant.OPERATOR_ID;
import static com.ms.rest.conf.security.jwt.ClaimsKeyConstant.OPERATOR_ROLE;

@Component
@EnableConfigurationProperties(value = {
        JWTProperties.class
})
@RequiredArgsConstructor
public class JWTFacade {

    @NonNull
    private final JWTProperties jwtProperties;

    public String generateUser(Object userId) {
        if(Objects.isNull(userId)){
            return null;
        }
        Map<String, Object> claims = Maps.newHashMap();
        claims.put(OPERATOR_ID, userId);
        claims.put(OPERATOR_ROLE, OperatorRoleEnum.USER.name());
        return JWTUtils.generateToken(jwtProperties, String.valueOf(userId), claims);
    }

    public String generateAdmin(Object userId) {
        if(Objects.isNull(userId)){
            return null;
        }
        Map<String, Object> claims = Maps.newHashMap();
        claims.put(OPERATOR_ID, userId);
        claims.put(OPERATOR_ROLE, OperatorRoleEnum.ADMIN.name());
        return JWTUtils.generateToken(jwtProperties, String.valueOf(userId), claims);
    }

    public String generate(Object userId,String role) {
        if(Objects.isNull(userId)){
            return null;
        }
        Map<String, Object> claims = Maps.newHashMap();
        claims.put(OPERATOR_ID, userId);
        claims.put(OPERATOR_ROLE,role);
        return JWTUtils.generateToken(jwtProperties, String.valueOf(userId), claims);
    }

    public String generate(String subject, Map<String, Object> claims) {
        return JWTUtils.generateToken(jwtProperties, subject, claims);
    }

    public Claims parse(String token) {
        return JWTUtils.parseClaims(jwtProperties, token);
    }

}
