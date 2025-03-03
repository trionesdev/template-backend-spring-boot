package com.trionesdev.template.rest.tenant.domains.perm.controller.ro;

import com.trionesdev.template.core.domains.perm.shared.enums.ClientType;
import com.trionesdev.template.core.domains.perm.shared.enums.PermissionSubjectType;
import lombok.Data;

@Data
public class PermissionQueryRO {
    private String appCoe;
    private ClientType clientType;
    private PermissionSubjectType subjectType;
    private String subject;
}
