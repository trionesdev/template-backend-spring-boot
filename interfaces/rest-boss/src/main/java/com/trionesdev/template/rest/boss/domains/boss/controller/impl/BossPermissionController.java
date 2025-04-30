package com.trionesdev.template.rest.boss.domains.boss.controller.impl;

import cn.hutool.core.lang.tree.Tree;
import com.trionesdev.template.core.domains.boss.dto.perm.BossPermissionResourceDTO;
import com.trionesdev.template.core.domains.boss.dto.perm.BossPolicyDTO;
import com.trionesdev.template.core.domains.boss.service.impl.BossPermissionService;
import com.trionesdev.template.core.domains.boss.shared.enums.ClientType;
import com.trionesdev.template.rest.boss.domains.boss.controller.ro.perm.BossPermissionPolicySaveRO;
import com.trionesdev.template.rest.boss.domains.boss.controller.ro.perm.BossPermissionQueryRO;
import com.trionesdev.template.rest.boss.domains.boss.internal.BossPermRestBossConvert;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static com.trionesdev.template.rest.boss.domains.boss.internal.BossConstants.BOSS_PERM_PATH;

@Tag(name = "权限/权限策略")
@RequiredArgsConstructor
@RestController(value = "boss_bossPermissionController")
@RequestMapping(BOSS_PERM_PATH)
public class BossPermissionController {
    private final BossPermRestBossConvert convert;
    private final BossPermissionService permissionService;

    @Operation(summary = "保存策略")
    @PutMapping(value = "policy/save")
    public void savePolicy(@Validated @RequestBody BossPermissionPolicySaveRO args) {
        var policy = convert.policySaveCmdFromRo(args);
        permissionService.savePolicy(policy);
    }


    @Operation(summary = "查询权限列表")
    @GetMapping(value = "policy/permissions")
    public Set<BossPermissionResourceDTO> findObjPermissions(BossPermissionQueryRO query) {
        return permissionService.findPermissionsBySubject(query.getClientType(), query.getSubjectType(), query.getSubject());
    }

    @Operation(summary = "查询当前用户权限列表")
    @GetMapping(value = "actor/policy")
    public BossPolicyDTO actorPermissions(
            @RequestParam(value = "clientType", required = false) ClientType clientType
    ) {
        return permissionService.findActorPolicy(clientType);
    }

    @Operation(summary = "获取有权限的菜单")
    @GetMapping(value = "actor/policy/menu")
    public List<Tree<String>> findActorMenuTree(
            @RequestParam(value = "clientType", required = false) ClientType clientType,
            @RequestParam(value = "group", required = false) String group
    ) {
        return permissionService.findActorMenuTree(clientType, group);
    }

}
