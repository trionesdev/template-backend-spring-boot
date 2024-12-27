package com.trionesdev.template.rest.tenant.domains.perm.controller.impl;

import cn.hutool.core.lang.tree.Tree;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.template.core.domains.perm.dao.criteria.RoleGrantCriteria;
import com.trionesdev.template.core.domains.perm.dao.po.RolePO;
import com.trionesdev.template.core.domains.perm.dto.RoleMemberDTO;
import com.trionesdev.template.core.domains.perm.service.impl.RoleService;
import com.trionesdev.template.rest.tenant.domains.perm.controller.ro.RemoveRoleGrantRO;
import com.trionesdev.template.rest.tenant.domains.perm.controller.ro.RoleCreateRO;
import com.trionesdev.template.rest.tenant.domains.perm.controller.ro.RoleGrantRO;
import com.trionesdev.template.rest.tenant.domains.perm.controller.ro.RoleUpdateRO;
import com.trionesdev.template.rest.tenant.domains.perm.internal.PermBeRestConvert;
import com.trionesdev.template.rest.tenant.domains.perm.internal.PermRestConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "权限-角色")
@RequiredArgsConstructor
@RestController
@RequestMapping(PermRestConstants.PERM_PATH)
public class RoleController {
    private final PermBeRestConvert convert;
    private final RoleService roleService;

    @Operation(summary = "创建角色")
    @PostMapping(value = "roles")
    public void createRole(@Validated @RequestBody RoleCreateRO args) {
        var record = convert.from(args);
        roleService.create(record);
    }

    @Operation(summary = "根据ID删除角色")
    @DeleteMapping(value = "roles/{id}")
    public void deleteRoleById(@PathVariable String id) {
        roleService.deleteById(id);
    }

    @Operation(summary = "根据ID更新角色")
    @PutMapping(value = "roles/{id}")
    public void updateRoleById(@PathVariable String id, @Validated @RequestBody RoleUpdateRO args) {
        var record = convert.from(args);
        record.setId(id);
        roleService.updateById(record);
    }

    @Operation(summary = "根据ID获取角色")
    @GetMapping(value = "roles/{id}")
    public RolePO findRoleById(@PathVariable String id) {
        return roleService.findById(id).orElse(null);
    }

    @Operation(summary = "获取角色树形列表")
    @GetMapping(value = "role/tree")
    public List<Tree<String>> findRoleTree() {
        return roleService.findRoleTree();
    }

    @Operation(summary = "角色授予")
    @PutMapping(value = "roles/{id}/grant")
    public void grantRole(@PathVariable String id, @Validated @RequestBody RoleGrantRO args) {
        var cmd = convert.from(args);
        cmd.setRoleId(id);
        roleService.roleGrant(cmd);
    }

    @Operation(summary = "移除角色授予对象(批量)")
    @PutMapping(value = "roles/{id}/grants/remove/batch")
    public void removeRoleGrantBatch(@PathVariable String id, @Validated @RequestBody RemoveRoleGrantRO args) {
        var cmd = convert.from(args);
        cmd.setRoleId(id);
        roleService.removeRoleGrantByObjs(cmd);
    }

    @Operation(summary = "根据ID获取角色成员(分页)")
    @GetMapping(value = "roles/{id}/member/page")
    public PageInfo<RoleMemberDTO> queryRoleMembersPage(
            @PathVariable String id,
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize
    ) {
        var criteria = RoleGrantCriteria.builder().pageNum(pageNum).pageSize(pageSize).roleId(id).build();
        return roleService.queryRoleMemberPage(criteria);
    }

}
