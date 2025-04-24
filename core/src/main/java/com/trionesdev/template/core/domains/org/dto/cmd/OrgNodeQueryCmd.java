package com.trionesdev.template.core.domains.org.dto.cmd;

import com.trionesdev.template.core.domains.org.shared.enums.OrgNodeType;
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
