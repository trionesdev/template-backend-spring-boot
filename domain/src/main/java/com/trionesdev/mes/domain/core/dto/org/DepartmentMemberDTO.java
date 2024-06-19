package com.trionesdev.mes.domain.core.dto.org;

import com.trionesdev.mes.domain.core.dto.tenant.TenantMemberDTO;
import lombok.Data;

@Data
public class DepartmentMemberDTO {
    private String id;
    private String departmentId;
    private String memberId;
    private TenantMemberDTO member;
}
