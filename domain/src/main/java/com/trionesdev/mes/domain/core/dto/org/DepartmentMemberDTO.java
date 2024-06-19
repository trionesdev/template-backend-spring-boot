package com.trionesdev.mes.domain.core.dto.org;

import com.trionesdev.mes.domain.core.domains.tenant.repository.po.TenantMemberPO;
import lombok.Data;

@Data
public class DepartmentMemberDTO {
    private String id;
    private String departmentId;
    private String memberId;
    private TenantMemberPO member;
}
