package com.trionesdev.template.core.domains.boss.dto.role.cmd;

import com.trionesdev.template.core.domains.boss.shared.enums.RoleSubjectType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BossRoleGrantsRemoveCmd {
    private String roleId;
    private RoleSubjectType subjectType;
    private List<String> subjects;
}
