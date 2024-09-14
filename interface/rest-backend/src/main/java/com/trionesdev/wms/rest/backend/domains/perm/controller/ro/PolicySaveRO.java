package com.trionesdev.wms.rest.backend.domains.perm.controller.ro;

import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.wms.core.domains.perm.internal.enums.PolicyGrantObjType;
import lombok.Data;

import java.util.Set;

@Data
public class PolicySaveRO {
    ClientType clientType;
    private PolicyGrantObjType grantObjType;
    private String grantObjId;
    private Set<Permission> permissions;

    @Data
    public static class Permission {
        private String obj;
        private String act;
    }

}
