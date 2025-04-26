package com.trionesdev.template.core.domains.tenant.dto;

import lombok.Data;

@Data
public class TenantDTO {
    private String id;
    private String parentId;
    private String serial;
    private String name;
    private String logo;
    private String description;
}
