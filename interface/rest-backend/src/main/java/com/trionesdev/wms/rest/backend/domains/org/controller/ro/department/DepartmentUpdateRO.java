package com.trionesdev.wms.rest.backend.domains.org.controller.ro.department;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;

@Data
public class DepartmentUpdateRO {
    @NonNull
    private String parentId;
    @NotBlank
    private String name;
    private String description;
}
