package com.trionesdev.wms.rest.backend.domains.perm.controller.ro;

import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.wms.core.domains.perm.internal.enums.PermissionSubjectType;
import lombok.Data;

@Data
public class PermissionQueryRO {
    private ClientType clientType;
    private PermissionSubjectType subjectType;
    private String subject;
}
