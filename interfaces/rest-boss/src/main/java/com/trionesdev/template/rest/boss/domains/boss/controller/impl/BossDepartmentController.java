package com.trionesdev.template.rest.boss.domains.boss.controller.impl;

import com.trionesdev.template.core.domains.boss.service.impl.BossDepartmentService;
import com.trionesdev.template.rest.boss.domains.boss.controller.ro.department.BossDepartmentCreateRO;
import com.trionesdev.template.rest.boss.domains.boss.controller.ro.department.BossDepartmentUpdateRO;
import com.trionesdev.template.rest.boss.domains.boss.internal.BossDepartmentRestBossConvert;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "根据ID修改部门")
    @PutMapping(value = "departments/{id}")
    public void updateDepartmentById(@PathVariable(value = "id") String id, @Validated @RequestBody BossDepartmentUpdateRO args) {
        var department = convert.departmentFromUpdateRo(args);
        department.setId(id);
        departmentService.updateDepartmentById(department);
    }

}
