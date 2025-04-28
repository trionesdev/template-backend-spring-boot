package com.trionesdev.template.core.domains.boss.internal;

import com.trionesdev.template.core.domains.boss.dao.po.BossDepartmentPO;
import com.trionesdev.template.core.domains.boss.dto.department.BossDepartmentDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
public interface BossDepartmentDomainConvert {


    BossDepartmentDTO departmentPoToDto(BossDepartmentPO bossDepartmentPO);
}
