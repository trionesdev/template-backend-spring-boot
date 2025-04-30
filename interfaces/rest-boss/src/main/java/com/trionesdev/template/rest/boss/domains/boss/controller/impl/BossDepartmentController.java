package com.trionesdev.template.rest.boss.domains.boss.controller.impl;

import cn.hutool.core.lang.tree.Tree;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.template.core.domains.boss.dao.po.BossDepartmentPO;
import com.trionesdev.template.core.domains.boss.dto.department.BossDepartmentDTO;
import com.trionesdev.template.core.domains.boss.dto.department.BossDepartmentMemberDTO;
import com.trionesdev.template.core.domains.boss.dto.department.BossOrgNodeDTO;
import com.trionesdev.template.core.domains.boss.dto.department.cmd.BossOrgNodeQuery;
import com.trionesdev.template.core.domains.boss.service.impl.BossDepartmentService;
import com.trionesdev.template.rest.boss.domains.boss.controller.ro.department.BossDepartmentCreateRO;
import com.trionesdev.template.rest.boss.domains.boss.controller.ro.department.BossDepartmentMemberQueryRO;
import com.trionesdev.template.rest.boss.domains.boss.controller.ro.department.BossDepartmentOrgNodesQueryRO;
import com.trionesdev.template.rest.boss.domains.boss.controller.ro.department.BossDepartmentUpdateRO;
import com.trionesdev.template.rest.boss.domains.boss.internal.BossDepartmentRestBossConvert;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.trionesdev.template.rest.boss.domains.boss.internal.BossConstants.BOSS_DEPARTMENT_PATH;

@Tag(name = "BOSS/用户")
@RequiredArgsConstructor
@RestController("boss_departmentController")
@RequestMapping(BOSS_DEPARTMENT_PATH)
public class BossDepartmentController {
    private final BossDepartmentRestBossConvert convert;
    private final BossDepartmentService departmentService;

    @Operation(summary = "新建部门")
    @PostMapping(value = "departments")
    public void createDepartment(@Validated @RequestBody BossDepartmentCreateRO args) {
        var department = convert.departmentFromCreateRo(args);
        departmentService.createDepartment(department);
    }

    @Operation(summary = "根据ID删除部门")
    @DeleteMapping("departments/{id}")
    public void deleteDepartmentById(@PathVariable String id) {
        departmentService.deleteDepartmentById(id);
    }


    @Operation(summary = "根据ID修改部门")
    @PutMapping(value = "departments/{id}")
    public void updateDepartmentById(@PathVariable(value = "id") String id, @Validated @RequestBody BossDepartmentUpdateRO args) {
        var department = convert.departmentFromUpdateRo(args);
        department.setId(id);
        departmentService.updateDepartmentById(department);
    }

    @Operation(summary = "根据ID查询部门")
    @GetMapping("departments/{id}")
    public BossDepartmentPO queryDepartmentById(@PathVariable String id) {
        return departmentService.findDepartmentById(id).orElse(null);
    }

    @Operation(summary = "部门树形结构")
    @GetMapping("department/tree")
    public List<Tree<String>> getDepartmentTree() {
        return departmentService.departmentTree();
    }

    @Operation(summary = "获取部门路径")
    @GetMapping(value = "departments/{id}/paths")
    public List<BossDepartmentDTO> queryDepartmentPaths(@PathVariable String id) {
        return departmentService.findDepartmentPaths(id);
    }

    @Operation(summary = "根据ID删除部门成员")
    @DeleteMapping(value = "department/members/{id}")
    public void deleteDepartmentMemberById(@PathVariable String id) {
        departmentService.deleteDepartmentMemberById(id);
    }

    @Operation(summary = "查询部门成员列表分页")
    @GetMapping("department/member/page")
    public PageInfo<BossDepartmentMemberDTO> queryDepartmentMembersPage(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize,
            BossDepartmentMemberQueryRO query
    ) {
        var criteria = convert.departmentCriteriaFromQueryRo(query);
        criteria.setPageNum(pageNum);
        criteria.setPageSize(pageSize);
        return departmentService.findDepartmentMembersPage(criteria);
    }


    @Operation(summary = "查询组织列表(包含组织下成员)")
    @GetMapping("department/org/list")
    public List<BossOrgNodeDTO> queryDepartmentOrgList(BossDepartmentOrgNodesQueryRO query) {
        return departmentService.findDepartmentOrgNodes(BossOrgNodeQuery.builder().departmentId(query.getDepartmentId()).type(query.getType()).build());
    }

}
