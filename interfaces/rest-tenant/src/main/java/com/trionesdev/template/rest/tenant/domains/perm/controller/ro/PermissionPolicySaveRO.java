package com.trionesdev.template.rest.tenant.domains.perm.controller.ro;

import com.trionesdev.template.core.domains.perm.shared.enums.ClientType;
import com.trionesdev.template.core.domains.perm.shared.enums.PermissionSubjectType;
import com.trionesdev.template.core.domains.perm.shared.model.PermissionResource;
import lombok.Data;

import java.util.Set;

@Data
public class PermissionPolicySaveRO {
    private String appCode;
    private ClientType clientType;
    private PermissionSubjectType subjectType;
    private String subject;
    private Set<PermissionResource> permissions;

}
