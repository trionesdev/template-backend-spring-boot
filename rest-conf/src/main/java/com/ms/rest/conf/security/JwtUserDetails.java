package com.ms.rest.conf.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtUserDetails {
    private String token;
    private String operateId;
    private String role;
}
