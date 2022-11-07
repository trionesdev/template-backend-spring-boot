package ms.triones.infrastructure.conf.jwt;


import com.google.common.collect.Maps;
import com.moensun.commons.context.actor.ActorRoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Objects;


@RequiredArgsConstructor
@Configuration
@EnableConfigurationProperties(value = {
        JwtProperties.class
})
public class JwtFacade {

    private final JwtProperties jwtProperties;

    public String generateUser(Object userId) {
        if (Objects.isNull(userId)) {
            return null;
        }
        Map<String, Object> claims = Maps.newHashMap();
        claims.put(ClaimsKeyConstant.ACTOR_ID, userId);
        claims.put(ClaimsKeyConstant.ACTOR_ROLE, ActorRoleEnum.USER.name());
        return JwtUtils.generateToken(jwtProperties, String.valueOf(userId), claims);
    }

    public String generateBossUser(Object userId) {
        if (Objects.isNull(userId)) {
            return null;
        }
        Map<String, Object> claims = Maps.newHashMap();
        claims.put(ClaimsKeyConstant.ACTOR_ID, userId);
        claims.put(ClaimsKeyConstant.ACTOR_ROLE, ActorRoleEnum.BOSS_USER.name());
        return JwtUtils.generateToken(jwtProperties, String.valueOf(userId), claims);
    }

    public String generate(Object userId, String role) {
        if (Objects.isNull(userId)) {
            return null;
        }
        Map<String, Object> claims = Maps.newHashMap();
        claims.put(ClaimsKeyConstant.ACTOR_ID, userId);
        claims.put(ClaimsKeyConstant.ACTOR_ROLE, role);
        return JwtUtils.generateToken(jwtProperties, String.valueOf(userId), claims);
    }

    public String generate(String subject, Map<String, Object> claims) {
        return JwtUtils.generateToken(jwtProperties, subject, claims);
    }

    public Map<String, Object> parse(String token) {
        return JwtUtils.parseClaims(jwtProperties, token);
    }

}
