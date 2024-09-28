package com.trionesdev.wms.rest.backend.domains.perm.controller.impl;

import cn.hutool.core.lang.tree.Tree;
import com.trionesdev.wms.core.domains.perm.dto.PermissionDTO;
import com.trionesdev.wms.core.domains.perm.dto.PolicyDTO;
import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.wms.core.domains.perm.service.impl.PolicyService;
import com.trionesdev.wms.rest.backend.domains.perm.controller.ro.PermissionQueryRO;
import com.trionesdev.wms.rest.backend.domains.perm.controller.ro.PolicySaveRO;
import com.trionesdev.wms.rest.backend.domains.perm.internal.PermBeRestConvert;
import com.trionesdev.wms.rest.backend.domains.perm.internal.PermRestConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@Tag(name = "权限-策略")
@RequiredArgsConstructor
@RestController
@RequestMapping(PermRestConstants.PERM_PATH)
public class PolicyController {
    private final PermBeRestConvert convert;
    private final PolicyService policyService;

    @Operation(summary = "保存策略")
    @PutMapping(value = "policy/save")
    public void savePolicy(@Validated @RequestBody PolicySaveRO args) {
        var policy = convert.from(args);
        policyService.savePolicy(policy);
    }


    @Operation(summary = "查询权限列表")
    @GetMapping(value = "policy/permissions")
    public Set<PermissionDTO> findObjPermissions(PermissionQueryRO query) {
        return policyService.findPermissionsBySubject(query.getAppCoe(), query.getClientType(), query.getSubjectType(), query.getSubject());
    }

    @Operation(summary = "查询当前用户权限列表")
    @GetMapping(value = "actor/policy")
    public PolicyDTO actorPermissions(
            @RequestParam(value = "appCode", required = false) String appCode,
            @RequestParam(value = "clientType", required = false) ClientType clientType
    ) {
        return policyService.findActorPolicy(appCode, clientType);
    }

    @Operation(summary = "获取有权限的菜单")
    @GetMapping(value = "actor/policy/menu")
    public List<Tree<String>> findActorMenuTree(
            @RequestParam(value = "appCode", required = false) String appCode,
            @RequestParam(value = "clientType", required = false) ClientType clientType,
            @RequestParam(value = "group", required = false) String group
    ) {
        return policyService.findActorMenuTree(appCode, clientType, group);
    }

}
