package com.trionesdev.wms.rest.backend.domains.org.controller.ro.department;

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
