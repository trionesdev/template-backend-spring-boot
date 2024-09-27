package com.trionesdev.wms.core.domains.perm.service.impl;

import com.trionesdev.commons.context.actor.ActorContext;
import com.trionesdev.wms.core.domains.perm.dao.po.RolePO;
import com.trionesdev.wms.core.domains.perm.dto.PermissionDTO;
import com.trionesdev.wms.core.domains.perm.dto.PolicyDTO;
import com.trionesdev.wms.core.domains.perm.dto.PolicySaveCmd;
import com.trionesdev.wms.core.domains.perm.internal.PermDomainConvert;
import com.trionesdev.wms.core.domains.perm.internal.aggregate.entity.Permission;
import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.wms.core.domains.perm.internal.enums.PermissionSubjectType;
import com.trionesdev.wms.core.domains.perm.internal.enums.RoleSubjectType;
import com.trionesdev.wms.core.domains.perm.manager.impl.PolicyManager;
import com.trionesdev.wms.core.domains.perm.manager.impl.RoleManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PolicyService {
    private final PermDomainConvert convert;
    private final ActorContext actorContext;
    private final PolicyManager policyManager;
    private final RoleManager roleManager;

    public void savePolicy(PolicySaveCmd dto) {
        var policy = convert.policyDtoToEntity(dto);
        policyManager.savePolicy(policy);
    }

    private Set<PermissionDTO> assemblePermissions(Set<Permission> permissions) {
        return permissions.stream().map(convert::permissionEntityToDto).collect(Collectors.toSet());
    }

    public Set<PermissionDTO> findPermissionsBySubject(ClientType clientType, PermissionSubjectType grantObjType, String grantObjId) {
        return assemblePermissions(policyManager.findPermissionsBySubject(clientType, grantObjType, grantObjId));
    }

    public PolicyDTO findActorPolicy(ClientType clientType) {
        Set<RolePO> roles = roleManager.findObjRelationRoles(RoleSubjectType.USER, actorContext.getUserId());
        var roleIds = roles.stream().map(RolePO::getId).collect(Collectors.toSet());
        var permissions = policyManager.findPermissionsBySubjects(clientType, PermissionSubjectType.ROLE, roleIds);
        return PolicyDTO.builder().permissions(assemblePermissions(permissions)).build();
    }

}
