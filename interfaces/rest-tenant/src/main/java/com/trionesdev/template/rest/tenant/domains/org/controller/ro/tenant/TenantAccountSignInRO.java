package com.trionesdev.template.rest.tenant.domains.org.controller.ro.tenant;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TenantAccountSignInRO {
    private String tenantSerial;
    @NotBlank
    private String account;
    @NotBlank
    private String password;
}
