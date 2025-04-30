package com.trionesdev.template.rest.boss.domains.boss.controller.ro.department;

import com.trionesdev.template.core.domains.boss.shared.enums.OrgNodeType;
import lombok.Data;

@Data
public class BossDepartmentOrgNodesQueryRO {
    private String departmentId;
    private OrgNodeType type;
}
