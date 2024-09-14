package com.trionesdev.wms.rest.backend.domains.perm.controller.impl;

import cn.hutool.core.lang.tree.Tree;
import com.trionesdev.wms.core.domains.perm.dao.po.RolePO;
import com.trionesdev.wms.core.domains.perm.service.impl.RoleService;
import com.trionesdev.wms.rest.backend.domains.perm.controller.ro.RoleCreateRO;
import com.trionesdev.wms.rest.backend.domains.perm.controller.ro.RoleUpdateRO;
import com.trionesdev.wms.rest.backend.domains.perm.internal.PermBeRestConvert;
import com.trionesdev.wms.rest.backend.domains.perm.internal.PermRestConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
