package com.trionesdev.template.rest.boss.domains.boss.controller.ro.department;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BossDepartmentCreateRO {
    @NotNull
    private String parentId;
    @NotBlank
    private String name;
}
