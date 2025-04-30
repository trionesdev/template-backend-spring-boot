package com.trionesdev.template.core.domains.boss.dto.perm.cmd;

import com.trionesdev.template.core.domains.boss.shared.enums.ClientType;
import com.trionesdev.template.core.domains.boss.shared.enums.PermissionSubjectType;
import com.trionesdev.template.core.domains.boss.shared.model.BossPermissionResource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BossPermissionPolicySaveCmd {
    private String appCode;
    private ClientType clientType;
    private PermissionSubjectType subjectType;
    private String subject;
    private Set<BossPermissionResource> permissions;
}
