package com.trionesdev.template.core.domains.org.dto;

import lombok.Data;

@Data
public class BossCreateTenantCmd {
    private String name;
    private String description;
    private String phone;
    private String email;
    private String username;
    private String nickname;
    private String password;
    private String validationCode;
}
