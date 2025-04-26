package com.trionesdev.template.core.domains.tenant.dto.cmd;

import lombok.Data;

@Data
public class BossUserCreateTenantCmd {
    private String name;
    private String description;
    private String phone;
    private String email;
    private String username;
    private String nickname;
    private String password;
    private String validationCode;
}
