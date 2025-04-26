package com.trionesdev.template.rest.tenant.domains.tenant.controller.ro.department;

import com.trionesdev.template.core.domains.tenant.shared.enums.OrgNodeType;
import lombok.Data;

@Data
public class DepartmentOrgNodesQueryRO {
    private String departmentId;
    private OrgNodeType type;
}
