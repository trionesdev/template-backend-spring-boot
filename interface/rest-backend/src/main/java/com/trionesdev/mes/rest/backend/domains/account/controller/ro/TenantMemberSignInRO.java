package com.trionesdev.mes.rest.backend.domains.account.controller.ro;

import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
public class TenantMemberSignInRO {
    private String tenantSerial;
    private String username;
    private String password;
}
