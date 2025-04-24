package com.trionesdev.template.rest.tenant.domains.org.controller.ro.department;

import com.trionesdev.template.core.domains.org.shared.enums.OrgNodeType;
import lombok.Data;

@Data
public class DepartmentOrgNodesQueryRO {
    private String departmentId;
    private OrgNodeType type;
}
