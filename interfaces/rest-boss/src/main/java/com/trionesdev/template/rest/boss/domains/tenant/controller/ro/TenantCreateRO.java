package com.trionesdev.template.rest.boss.domains.tenant.controller.ro;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TenantCreateRO {
    @NotBlank
    private String name;
    private String description;
    @NotBlank
    private String phone;
    @NotBlank
    private String password;
    @NotBlank
    private String validationCode;
}
