package com.trionesdev.wms.core.domains.perm.service.impl;

import com.trionesdev.wms.core.domains.perm.dto.PermissionDTO;
import com.trionesdev.wms.core.domains.perm.dto.PolicyDTO;
import com.trionesdev.wms.core.domains.perm.internal.PermDomainConvert;
import com.trionesdev.wms.core.domains.perm.internal.aggregate.entity.Permission;
import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.wms.core.domains.perm.internal.enums.PolicyGrantObjType;
import com.trionesdev.wms.core.domains.perm.manager.impl.PolicyManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PolicyService {
    private final PermDomainConvert convert;
    private final PolicyManager policyManager;

    public void savePolicy(PolicyDTO dto) {
        var policy = convert.policyDtoToEntity(dto);
        policyManager.savePolicy(policy);
    }

    private Set<PermissionDTO> assemblePermissions(Set<Permission> permissions) {
        return permissions.stream().map(convert::permissionEntityToDto).collect(Collectors.toSet());
    }

    public Set<PermissionDTO> findPermissionsByGrantObj(ClientType clientType, PolicyGrantObjType grantObjType, String grantObjId) {
        return assemblePermissions(policyManager.findPermissionsByGrantObj(clientType, grantObjType, grantObjId));
    }

}
