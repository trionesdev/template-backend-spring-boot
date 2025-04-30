package com.trionesdev.template.rest.boss.domains.boss.controller.impl;

import cn.hutool.core.lang.tree.Tree;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.template.core.domains.boss.dao.criteria.BossRoleGrantCriteria;
import com.trionesdev.template.core.domains.boss.dao.po.BossRolePO;
import com.trionesdev.template.core.domains.boss.dto.role.BossRoleMemberDTO;
import com.trionesdev.template.core.domains.boss.service.impl.BossRoleService;
import com.trionesdev.template.rest.boss.domains.boss.controller.ro.role.BossRoleCreateRO;
import com.trionesdev.template.rest.boss.domains.boss.controller.ro.role.BossRoleGrantCreateRO;
import com.trionesdev.template.rest.boss.domains.boss.controller.ro.role.BossRoleGrantRemoveRO;
import com.trionesdev.template.rest.boss.domains.boss.controller.ro.role.BossRoleUpdateRO;
import com.trionesdev.template.rest.boss.domains.boss.internal.BossRoleRestBossConvert;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.trionesdev.template.rest.boss.domains.boss.internal.BossConstants.BOSS_ROLE_PATH;

@Tag(name = "Boss/角色")
@RequiredArgsConstructor
@RestController
@RequestMapping(BOSS_ROLE_PATH)
public class BossRoleController {
    private final BossRoleRestBossConvert convert;
    private final BossRoleService roleService;

    @Operation(summary = "创建角色")
    @PostMapping(value = "roles")
    public void createRole(@Validated @RequestBody BossRoleCreateRO args) {
        var record = convert.rolePoFromCreateRo(args);
        roleService.create(record);
    }

    @Operation(summary = "根据ID删除角色")
    @DeleteMapping(value = "roles/{id}")
    public void deleteRoleById(@PathVariable String id) {
        roleService.deleteById(id);
    }

    @Operation(summary = "根据ID更新角色")
    @PutMapping(value = "roles/{id}")
    public void updateRoleById(@PathVariable String id, @Validated @RequestBody BossRoleUpdateRO args) {
        var record = convert.rolePoFromUpdateRo(args);
        record.setId(id);
        roleService.updateById(record);
    }

    @Operation(summary = "根据ID获取角色")
    @GetMapping(value = "roles/{id}")
    public BossRolePO findRoleById(@PathVariable String id) {
        return roleService.findById(id).orElse(null);
    }

    @Operation(summary = "获取角色树形列表")
    @GetMapping(value = "role/tree")
    public List<Tree<String>> findRoleTree() {
        return roleService.findRoleTree();
    }

    @Operation(summary = "角色授予")
    @PutMapping(value = "roles/{id}/grant")
    public void grantRole(@PathVariable String id, @Validated @RequestBody BossRoleGrantCreateRO args) {
        var cmd = convert.roleGrantFromCreateRo(args);
        cmd.setRoleId(id);
        roleService.createRoleGrants(cmd);
    }

    @Operation(summary = "移除角色授予对象(批量)")
    @PutMapping(value = "roles/{id}/grants/remove/batch")
    public void removeRoleGrantBatch(@PathVariable String id, @Validated @RequestBody BossRoleGrantRemoveRO args) {
        var cmd = convert.roleGrantFromRemoveCreateRo(args);
        cmd.setRoleId(id);
        roleService.removeRoleGrantBySubjects(cmd);
    }

    @Operation(summary = "根据ID获取角色成员(分页)")
    @GetMapping(value = "roles/{id}/member/page")
    public PageInfo<BossRoleMemberDTO> queryRoleMembersPage(
            @PathVariable String id,
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize
    ) {
        var criteria = BossRoleGrantCriteria.builder().pageNum(pageNum).pageSize(pageSize).roleId(id).build();
        return roleService.queryRoleMemberPage(criteria);
    }

}
