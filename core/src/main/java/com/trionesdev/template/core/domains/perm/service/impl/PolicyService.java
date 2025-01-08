package com.trionesdev.template.core.domains.perm.service.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.trionesdev.commons.context.actor.ActorContext;
import com.trionesdev.commons.core.constant.IdentityConstants;
import com.trionesdev.commons.exception.BusinessException;
import com.trionesdev.template.core.domains.org.provider.OrgProvider;
import com.trionesdev.template.core.domains.perm.dao.criteria.FunctionalResourceCriteria;
import com.trionesdev.template.core.domains.perm.dao.po.RolePO;
import com.trionesdev.template.core.domains.perm.dto.PermissionDTO;
import com.trionesdev.template.core.domains.perm.dto.PolicyDTO;
import com.trionesdev.template.core.domains.perm.dto.PolicySaveCmd;
import com.trionesdev.template.core.domains.perm.internal.PermDomainConvert;
import com.trionesdev.template.core.domains.perm.internal.aggregate.entity.FunctionalResource;
import com.trionesdev.template.core.domains.perm.internal.aggregate.entity.Permission;
import com.trionesdev.template.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.template.core.domains.perm.internal.enums.FunctionalResourceType;
import com.trionesdev.template.core.domains.perm.internal.enums.PermissionSubjectType;
import com.trionesdev.template.core.domains.perm.internal.enums.RoleSubjectType;
import com.trionesdev.template.core.domains.perm.manager.impl.FunctionalResourceManager;
import com.trionesdev.template.core.domains.perm.manager.impl.PolicyManager;
import com.trionesdev.template.core.domains.perm.manager.impl.RoleManager;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.trionesdev.template.core.domains.perm.internal.PermError.TENANT_MEMBER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class PolicyService {
    private final PermDomainConvert convert;
    private final ActorContext actorContext;
    private final PolicyManager policyManager;
    private final FunctionalResourceManager functionalResourceManager;
    private final RoleManager roleManager;
    private final OrgProvider orgProvider;

    public void savePolicy(PolicySaveCmd dto) {
        var policy = convert.policyDtoToEntity(dto);
        policyManager.savePolicy(policy);
    }

    private Set<PermissionDTO> assemblePermissions(Set<Permission> permissions) {
        return permissions.stream().map(convert::permissionEntityToDto).collect(Collectors.toSet());
    }

    public Set<PermissionDTO> findPermissionsBySubject(String appCode, ClientType clientType, PermissionSubjectType grantObjType, String grantObjId) {
        return assemblePermissions(policyManager.findPermissionsBySubject(appCode, clientType, grantObjType, grantObjId));
    }

    public PolicyDTO findActorPolicy(String appCode, ClientType clientType) {
        Set<RolePO> roles = roleManager.findObjRelationRoles(RoleSubjectType.MEMBER, actorContext.getMemberId());
        var roleIds = roles.stream().map(RolePO::getId).collect(Collectors.toSet());
        var permissions = policyManager.findPermissionsBySubjects(appCode, clientType, PermissionSubjectType.ROLE, roleIds);
        return PolicyDTO.builder().permissions(assemblePermissions(permissions)).build();
    }

    private List<TreeNode<String>> assembleTreeNodes(List<FunctionalResource> resources) {
        if (CollectionUtils.isEmpty(resources)) {
            return Collections.emptyList();
        }
        return resources.stream().map(resource -> {
            var map = new HashMap<String, Object>();
            map.put("uniqueCode", resource.getUniqueCode());
            map.put("type", resource.getType());
            map.put("groupCode", resource.getGroupCoe());
            map.put("icon", resource.getIcon());
            map.put("description", resource.getDescription());
            map.put("apiCode", resource.getApiCode());
            map.put("routePath", resource.getRoutePath());
            var treeNode = new TreeNode<String>();
            treeNode.setId(resource.getId());
            treeNode.setParentId(resource.getParentId());
            treeNode.setName(resource.getName());
            treeNode.setExtra(map);
            return treeNode;
        }).collect(Collectors.toList());
    }

    public List<Tree<String>> findActorMenuTree(String appCode, ClientType clientType, String group) {
        var tenantMember = orgProvider.getMemberById(actorContext.getMemberId());
        if (Objects.isNull(tenantMember)) {
            throw new BusinessException(TENANT_MEMBER_NOT_FOUND);
        }
        var resources = functionalResourceManager.findResources(FunctionalResourceCriteria.builder()
                .appCode(appCode).clientType(clientType).groupCode(group)
                .build());
        if (CollectionUtils.isEmpty(resources)) {
            return Collections.emptyList();
        }
        String parentId = IdentityConstants.STRING_ID_ZERO_VALUE;
        if (StringUtils.isNotBlank(group)) {
            parentId = resources.stream().filter(resource -> resource.getUniqueCode().equals(group)).findFirst().map(FunctionalResource::getId).orElse(parentId);
        }
        if (BooleanUtils.isTrue(tenantMember.getMaster())) {
            var menus = resources.stream().filter(t -> Objects.equals(FunctionalResourceType.MENU, t.getType())).toList();
            return TreeUtil.build(assembleTreeNodes(menus), parentId);
        } else {
            Set<RolePO> roles = roleManager.findObjRelationRoles(RoleSubjectType.MEMBER, actorContext.getMemberId());
            var roleIds = roles.stream().map(RolePO::getId).collect(Collectors.toSet());
            var permissionObjs = policyManager.findPermissionsBySubjects(appCode, clientType, PermissionSubjectType.ROLE, roleIds).stream().map(Permission::getObj).collect(Collectors.toList());
            var permissionResources = resources.stream().filter(resource -> {
                return CollectionUtils.containsAny(permissionObjs, resource.getUniqueCode());
            }).toList();
            var menus = permissionResources.stream().filter(t -> Objects.equals(FunctionalResourceType.MENU, t.getType())).toList();
            return TreeUtil.build(assembleTreeNodes(menus), parentId);
        }
    }
}
