package com.trionesdev.mes.domain.core.dto.tenant;

import lombok.Data;

@Data
public class TenantDTO {
    private String id;
    private String parentId;
    private String name;
    private String logo;
    private String description;
}
