package com.trionesdev.template.rest.boss.domains.boss.internal;

import com.trionesdev.template.core.domains.boss.dao.po.BossDepartmentPO;
import com.trionesdev.template.rest.boss.domains.boss.controller.ro.department.BossDepartmentCreateRO;
import com.trionesdev.template.rest.boss.domains.boss.controller.ro.department.BossDepartmentUpdateRO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface BossDepartmentRestBossConvert {
    BossDepartmentPO departmentFromCreateRo(BossDepartmentCreateRO args);

    BossDepartmentPO departmentFromUpdateRo(BossDepartmentUpdateRO args);
}
