package com.trionesdev.mes.domain.core.domains.tenant.service.bo;

import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
public class TenantMemberSignInArg {
    private String tenantSerial;
    private String username;
    private String password;

    public Boolean passwordMatch(String encryptedPassword) {
        return new BCryptPasswordEncoder().matches(password, encryptedPassword);
    }

}
