package com.trionesdev.wms.rest.backend.domains.perm.controller.ro;

import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.wms.core.domains.perm.internal.enums.PolicyGrantObjType;
import lombok.Data;

@Data
public class PermissionQueryRO {
    private ClientType clientType;
    private PolicyGrantObjType grantObjType;
    private String grantObjId;
}
