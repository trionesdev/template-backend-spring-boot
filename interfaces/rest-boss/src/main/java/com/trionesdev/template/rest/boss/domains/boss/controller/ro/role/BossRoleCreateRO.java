package com.trionesdev.template.rest.boss.domains.boss.controller.ro.role;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BossRoleCreateRO {
    private String parentId = "0";
    @NotBlank
    private String name;
    private String description;
}
