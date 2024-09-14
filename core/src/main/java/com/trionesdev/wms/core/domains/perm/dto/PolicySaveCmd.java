package com.trionesdev.wms.core.domains.perm.dto;

import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.wms.core.domains.perm.internal.enums.PolicyGrantObjType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PolicySaveCmd {
    ClientType clientType;
    private PolicyGrantObjType grantObjType;
    private String grantObjId;
    private Set<PermissionDTO> permissions;
}
