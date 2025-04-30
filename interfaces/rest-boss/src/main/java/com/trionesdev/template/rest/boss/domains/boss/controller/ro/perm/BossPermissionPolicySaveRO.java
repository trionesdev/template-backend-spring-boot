package com.trionesdev.template.rest.boss.domains.boss.controller.ro.perm;

import com.trionesdev.template.core.domains.boss.shared.model.BossPermissionResource;
import com.trionesdev.template.core.domains.boss.shared.enums.ClientType;
import com.trionesdev.template.core.domains.boss.shared.enums.PermissionSubjectType;
import lombok.Data;

import java.util.Set;

@Data
public class BossPermissionPolicySaveRO {
    private String appCode;
    private ClientType clientType;
    private PermissionSubjectType subjectType;
    private String subject;
    private Set<BossPermissionResource> permissions;

}
