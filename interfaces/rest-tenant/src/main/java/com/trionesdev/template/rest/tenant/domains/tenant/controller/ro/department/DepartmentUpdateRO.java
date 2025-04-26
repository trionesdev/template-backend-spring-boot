package com.trionesdev.template.rest.tenant.domains.tenant.controller.ro.department;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DepartmentUpdateRO {
    @NotBlank
    private String parentId;
    @NotBlank
    private String name;
    private String description;
}
