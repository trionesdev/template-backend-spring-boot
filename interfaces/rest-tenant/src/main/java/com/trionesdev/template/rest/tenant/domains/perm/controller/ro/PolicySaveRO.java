package com.trionesdev.template.rest.tenant.domains.perm.controller.ro;

import com.trionesdev.template.core.domains.perm.shared.enums.ClientType;
import com.trionesdev.template.core.domains.perm.shared.enums.PermissionSubjectType;
import lombok.Data;

import java.util.Set;

@Data
public class PolicySaveRO {
    private String appCode;
    private ClientType clientType;
    private PermissionSubjectType subjectType;
    private String subject;
    private Set<Permission> permissions;

    @Data
    public static class Permission {
        private String obj;
        private String effect;
    }

}
