package com.trionesdev.template.core.domains.boss.service.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.trionesdev.commons.context.actor.ActorContext;
import com.trionesdev.commons.core.constant.IdentityConstants;
import com.trionesdev.commons.exception.BusinessException;
import com.trionesdev.template.core.domains.boss.dao.criteria.BossFunctionalResourceCriteria;
import com.trionesdev.template.core.domains.boss.dao.po.BossFunctionalResourcePO;
import com.trionesdev.template.core.domains.boss.dao.po.BossPermissionPO;
import com.trionesdev.template.core.domains.boss.dao.po.BossRolePO;
import com.trionesdev.template.core.domains.boss.dto.perm.BossPermissionResourceDTO;
import com.trionesdev.template.core.domains.boss.dto.perm.BossPolicyDTO;
import com.trionesdev.template.core.domains.boss.dto.perm.cmd.BossPermissionPolicySaveCmd;
import com.trionesdev.template.core.domains.boss.internal.BossPermDomainConvert;
import com.trionesdev.template.core.domains.boss.manager.impl.BossFunctionalResourceManager;
import com.trionesdev.template.core.domains.boss.manager.impl.BossPermissionManager;
import com.trionesdev.template.core.domains.boss.manager.impl.BossRoleManager;
import com.trionesdev.template.core.domains.boss.manager.impl.BossUserManager;
import com.trionesdev.template.core.domains.boss.shared.enums.ClientType;
import com.trionesdev.template.core.domains.boss.shared.enums.FunctionalResourceType;
import com.trionesdev.template.core.domains.boss.shared.enums.PermissionSubjectType;
import com.trionesdev.template.core.domains.boss.shared.enums.RoleSubjectType;
import com.trionesdev.template.core.domains.boss.shared.model.BossPermissionResource;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.trionesdev.template.core.domains.perm.internal.PermErrors.TENANT_MEMBER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class BossPermissionService {
    private final BossPermDomainConvert convert;
    private final ActorContext actorContext;
    private final BossPermissionManager permissionManager;
    private final BossFunctionalResourceManager functionalResourceManager;
    private final BossRoleManager roleManager;
    private final BossUserManager userManager;


    public void savePolicy(BossPermissionPolicySaveCmd cmd) {
        Set<BossPermissionPO> permissions = new HashSet<>();
        if (CollectionUtils.isNotEmpty(cmd.getPermissions())) {
            permissions = cmd.getPermissions().stream().map(permission -> {
                var permissionPO = convert.permissionResourceToPermissionPo(permission);
                return permissionPO;
            }).collect(Collectors.toSet());
        }
        permissionManager.saveSubjectPermissions( cmd.getClientType(), cmd.getSubjectType(), cmd.getSubject(), permissions);
    }

    private Set<BossPermissionResourceDTO> assemblePermissions(Set<BossPermissionResource> permissions) {
        return permissions.stream().map(convert::permissionEntityToDto).collect(Collectors.toSet());
    }

    public Set<BossPermissionResourceDTO> findPermissionsBySubject(  ClientType clientType, PermissionSubjectType grantObjType, String grantObjId) {
        return assemblePermissions(permissionManager.findPermissionsBySubject(  clientType, grantObjType, grantObjId));
    }

    public BossPolicyDTO findActorPolicy(ClientType clientType) {
        Set<BossRolePO> roles = roleManager.findObjRelationRoles(RoleSubjectType.USER, actorContext.getMemberId());
        var roleIds = roles.stream().map(BossRolePO::getId).collect(Collectors.toSet());
        var permissions = permissionManager.findPermissionsBySubjects( clientType, PermissionSubjectType.ROLE, roleIds);
        return BossPolicyDTO.builder().permissions(assemblePermissions(permissions)).build();
    }

    private List<TreeNode<String>> assembleTreeNodes(List<BossFunctionalResourcePO> resources) {
        if (CollectionUtils.isEmpty(resources)) {
            return Collections.emptyList();
        }
        return resources.stream().map(resource -> {
            var map = new HashMap<String, Object>();
            map.put("uniqueCode", resource.getUniqueCode());
            map.put("type", resource.getType());
            map.put("groupCode", resource.getGroupCode());
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

    public List<Tree<String>> findActorMenuTree( ClientType clientType, String group) {
        var user = userManager.findUserById(actorContext.getUserId()).orElse(null);
        if (Objects.isNull(user)) {
            throw new BusinessException(TENANT_MEMBER_NOT_FOUND);
        }
        var resources = functionalResourceManager.findResources(BossFunctionalResourceCriteria.builder()
                 .clientType(clientType).groupCode(group)
                .build());
        if (CollectionUtils.isEmpty(resources)) {
            return Collections.emptyList();
        }
        String parentId = IdentityConstants.STRING_ID_ZERO_VALUE;
        if (StringUtils.isNotBlank(group)) {
            parentId = resources.stream().filter(resource -> resource.getUniqueCode().equals(group)).findFirst().map(BossFunctionalResourcePO::getId).orElse(parentId);
        }
        if (BooleanUtils.isTrue(user.getMaster())) {
            var menus = resources.stream().filter(t -> Objects.equals(FunctionalResourceType.MENU, t.getType())).toList();
            return TreeUtil.build(assembleTreeNodes(menus), parentId);
        } else {
            Set<BossRolePO> roles = roleManager.findObjRelationRoles(RoleSubjectType.USER, actorContext.getMemberId());
            var roleIds = roles.stream().map(BossRolePO::getId).collect(Collectors.toSet());
            var resourceCodes = permissionManager.findPermissionsBySubjects( clientType, PermissionSubjectType.ROLE, roleIds).stream().map(BossPermissionResource::getResourceCode).collect(Collectors.toList());
            var permissionResources = resources.stream().filter(resource -> {
                return CollectionUtils.containsAny(resourceCodes, resource.getUniqueCode());
            }).toList();
            var menus = permissionResources.stream().filter(t -> Objects.equals(FunctionalResourceType.MENU, t.getType())).toList();
            return TreeUtil.build(assembleTreeNodes(menus), parentId);
        }
    }
}
