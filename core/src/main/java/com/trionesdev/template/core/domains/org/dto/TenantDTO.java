package com.trionesdev.template.core.domains.org.dto;

import lombok.Data;

@Data
public class TenantDTO {
    private String id;
    private String parentId;
    private String name;
    private String logo;
    private String description;
}
