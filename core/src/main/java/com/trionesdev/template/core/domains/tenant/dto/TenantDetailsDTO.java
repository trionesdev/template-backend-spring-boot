package com.trionesdev.template.core.domains.tenant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TenantDetailsDTO {
    private String id;
    private String parentId;
    private String serial;
    private String name;
    private String logo;
    private String description;
    private TenantMemberDTO master;
}
