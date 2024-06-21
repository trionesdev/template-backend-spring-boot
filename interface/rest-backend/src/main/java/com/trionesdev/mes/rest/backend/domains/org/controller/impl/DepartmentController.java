package com.trionesdev.mes.rest.backend.domains.org.controller.impl;

import cn.hutool.core.lang.tree.Tree;
import com.trionesdev.mes.domain.core.domains.org.repository.po.DepartmentPO;
import com.trionesdev.mes.domain.core.domains.org.service.DepartmentService;
import com.trionesdev.mes.domain.core.dto.org.DepartmentDTO;
import com.trionesdev.mes.rest.backend.domains.org.controller.ro.DepartmentRO;
import com.trionesdev.mes.rest.backend.domains.org.internal.OrgBeRestBeanConvert;
import com.trionesdev.mes.rest.backend.domains.org.internal.OrgRestConstants;
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

@Tag(name = "部门")
@RequiredArgsConstructor
@RestController
@RequestMapping(OrgRestConstants.ORG_PATH)
public class DepartmentController {
    private final OrgBeRestBeanConvert convert;
    private final DepartmentService departmentService;

    @Operation(summary = "创建部门")
    @PostMapping("departments")
    public void createDepartment(@Validated @RequestBody DepartmentRO.Create args) {
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
    public void updateDepartmentById(@PathVariable String id, @Validated @RequestBody DepartmentRO.Update args) {
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
    @GetMapping("departments/tree")
    public List<Tree<String>> getDepartmentTree() {
        return departmentService.departmentTree();
    }

}
