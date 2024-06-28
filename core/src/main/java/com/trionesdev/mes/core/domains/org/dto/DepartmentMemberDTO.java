package com.trionesdev.mes.core.domains.org.dto;

import lombok.Data;

@Data
public class DepartmentMemberDTO {
    private String id;
    private String departmentId;
    private String memberId;
    private TenantMemberDTO member;
}
