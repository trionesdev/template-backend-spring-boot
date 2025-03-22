package com.trionesdev.template.rest.tenant.domains.org.controller.ro.tenant;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TenantCreateRO {
    @NotBlank
    private String name;
    private String logo;
    private String description;
}
