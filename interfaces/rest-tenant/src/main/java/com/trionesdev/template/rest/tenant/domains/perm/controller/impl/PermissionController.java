package com.trionesdev.template.rest.tenant.domains.perm.controller.impl;

import cn.hutool.core.lang.tree.Tree;
import com.trionesdev.template.core.domains.perm.dto.PermissionResourceDTO;
import com.trionesdev.template.core.domains.perm.dto.PolicyDTO;
import com.trionesdev.template.core.domains.perm.service.impl.PermissionService;
import com.trionesdev.template.core.domains.perm.shared.enums.ClientType;
import com.trionesdev.template.rest.tenant.domains.perm.controller.ro.PermissionPolicySaveRO;
import com.trionesdev.template.rest.tenant.domains.perm.controller.ro.PermissionQueryRO;
import com.trionesdev.template.rest.tenant.domains.perm.internal.PermBeRestConvert;
import com.trionesdev.template.rest.tenant.domains.perm.internal.PermRestConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Tag(name = "权限/权限策略")
@RequiredArgsConstructor
@RestController
@RequestMapping(PermRestConstants.PERM_PATH)
public class PermissionController {
    private final PermBeRestConvert convert;
    private final PermissionService permissionService;

    @Operation(summary = "保存策略")
    @PutMapping(value = "policy/save")
    public void savePolicy(@Validated @RequestBody PermissionPolicySaveRO args) {
        var policy = convert.from(args);
        permissionService.savePolicy(policy);
    }


    @Operation(summary = "查询权限列表")
    @GetMapping(value = "policy/permissions")
    public Set<PermissionResourceDTO> findObjPermissions(PermissionQueryRO query) {
        return permissionService.findPermissionsBySubject(query.getAppCoe(), query.getClientType(), query.getSubjectType(), query.getSubject());
    }

    @Operation(summary = "查询当前用户权限列表")
    @GetMapping(value = "actor/policy")
    public PolicyDTO actorPermissions(
            @RequestParam(value = "appCode", required = false) String appCode,
            @RequestParam(value = "clientType", required = false) ClientType clientType
    ) {
        return permissionService.findActorPolicy(appCode, clientType);
    }

    @Operation(summary = "获取有权限的菜单")
    @GetMapping(value = "actor/policy/menu")
    public List<Tree<String>> findActorMenuTree(
            @RequestParam(value = "appCode", required = false) String appCode,
            @RequestParam(value = "clientType", required = false) ClientType clientType,
            @RequestParam(value = "group", required = false) String group
    ) {
        return permissionService.findActorMenuTree(appCode, clientType, group);
    }

}
