package com.trionesdev.template.core.domains.tenant.dto.cmd;

import com.trionesdev.template.core.domains.tenant.shared.enums.OrgNodeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class OrgNodeQueryCmd {
    private String departmentId;
    private OrgNodeType type;
    private String wd;
}
