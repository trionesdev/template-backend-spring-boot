package com.trionesdev.template.core.domains.boss.dto.department.cmd;

import com.trionesdev.template.core.domains.boss.shared.enums.OrgNodeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BossOrgNodeQuery {
    private String departmentId;
    private OrgNodeType type;
    private String wd;
}
