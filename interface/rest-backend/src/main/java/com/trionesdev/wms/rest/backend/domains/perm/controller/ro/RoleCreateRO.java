package com.trionesdev.wms.rest.backend.domains.perm.controller.ro;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoleCreateRO {
    private String parentId = "0";
    @NotBlank
    private String name;
    private String description;
}
