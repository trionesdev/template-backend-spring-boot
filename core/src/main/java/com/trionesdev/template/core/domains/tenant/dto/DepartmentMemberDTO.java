package com.trionesdev.template.core.domains.tenant.dto;

import lombok.Data;

@Data
public class DepartmentMemberDTO {
    private String id;
    private String departmentId;
    private String memberId;
    private TenantMemberDetailDTO member;
}
