package com.trionesdev.template.rest.boss.domains.boss.controller.ro.perm;

import com.trionesdev.template.core.domains.boss.shared.enums.ClientType;
import com.trionesdev.template.core.domains.boss.shared.enums.PermissionSubjectType;
import lombok.Data;

@Data
public class BossPermissionQueryRO {
    private ClientType clientType;
    private PermissionSubjectType subjectType;
    private String subject;
}
