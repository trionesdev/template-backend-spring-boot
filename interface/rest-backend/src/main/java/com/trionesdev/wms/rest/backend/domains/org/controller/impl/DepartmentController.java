package com.trionesdev.wms.rest.backend.domains.org.controller.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.util.EnumUtil;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.wms.core.domains.org.dto.DepartmentDTO;
import com.trionesdev.wms.core.domains.org.dto.DepartmentMemberDTO;
import com.trionesdev.wms.core.domains.org.dto.DepartmentTreeQuery;
import com.trionesdev.wms.core.domains.org.dto.OrgNodeDTO;
import com.trionesdev.wms.core.domains.org.service.DepartmentService;
import com.trionesdev.wms.rest.backend.domains.org.controller.ro.department.DepartmentCreateRO;
import com.trionesdev.wms.rest.backend.domains.org.controller.ro.department.DepartmentMemberQueryRO;
import com.trionesdev.wms.rest.backend.domains.org.controller.ro.department.DepartmentUpdateRO;
import com.trionesdev.wms.rest.backend.domains.org.internal.OrgBeRestConvert;
import com.trionesdev.wms.rest.backend.domains.org.internal.OrgRestConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "部门")
@RequiredArgsConstructor
@RestController
@RequestMapping(OrgRestConstants.ORG_PATH)
public class DepartmentController {
    private final OrgBeRestConvert convert;
    private final DepartmentService departmentService;

    @Operation(summary = "创建部门")
    @PostMapping("departments")
    public void createDepartment(@Validated @RequestBody DepartmentCreateRO args) {
        var po = convert.from(args);
        departmentService.createDepartment(po);
    }

    @Operation(summary = "根据ID删除部门")
    @DeleteMapping("departments/{id}")
    public void deleteDepartmentById(@PathVariable String id) {
        departmentService.deleteDepartmentById(id);
    }

    @Operation(summary = "根据ID更新部门")
    @PutMapping("departments/{id}")
    public void updateDepartmentById(@PathVariable String id, @Validated @RequestBody DepartmentUpdateRO args) {
        var po = convert.from(args);
        po.setId(id);
        departmentService.updateDepartmentById(po);
    }

    @Operation(summary = "根据ID查询部门")
    @GetMapping("departments/{id}")
    public DepartmentDTO queryDepartmentById(@PathVariable String id) {
        return departmentService.findDepartmentById(id).orElse(null);
    }

    @Operation(summary = "部门树形结构")
    @GetMapping("department/tree")
    public List<Tree<String>> getDepartmentTree(
            @RequestParam(value = "mode", required = false) String mode
    ) {
        return departmentService.departmentTree(DepartmentTreeQuery.builder().mode(EnumUtil.fromString(DepartmentTreeQuery.Mode.class, mode, null)).build());
    }

    @Operation(summary = "获取部门路径")
    @GetMapping(value = "departments/{id}/paths")
    public List<DepartmentDTO> queryDepartmentPaths(@PathVariable String id) {
        return departmentService.findDepartmentPaths(id);
    }

    @Operation(summary = "根据ID删除部门成员")
    @DeleteMapping(value = "department/members/{id}")
    public void deleteDepartmentMemberById(@PathVariable String id) {
        departmentService.deleteDepartmentMemberById(id);
    }

    @Operation(summary = "查询部门成员列表分页")
    @GetMapping("department/member/page")
    public PageInfo<DepartmentMemberDTO> queryDepartmentMembersPage(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize,
            DepartmentMemberQueryRO query
    ) {
        var criteria = convert.from(query);
        criteria.setPageNum(pageNum);
        criteria.setPageSize(pageSize);
        return departmentService.findDepartmentMembersPage(criteria);
    }


    @Operation(summary = "查询组织列表(包含组织下成员)")
    @GetMapping("department/org/list")
    public List<OrgNodeDTO> queryDepartmentOrgList(@RequestParam String departmentId) {
        return departmentService.orgList(departmentId);
    }

}
