package com.ms.rest.conf.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.jwt")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JWTProperties {
    private String secret;
    private int expiration;
}
