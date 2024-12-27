package com.trionesdev.template.rest.tenant.domains.org.controller.ro.department;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DepartmentCreateRO {
    @NotBlank
    private String parentId;
    @NotBlank
    private String name;
    private String description;
}
