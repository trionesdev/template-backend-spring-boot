package com.trionesdev.mes.domain.core.domains.org.internal;

import com.trionesdev.mes.domain.core.domains.org.repository.po.DepartmentMemberPO;
import com.trionesdev.mes.domain.core.domains.org.repository.po.DepartmentPO;
import com.trionesdev.mes.domain.core.dto.org.DepartmentDTO;
import com.trionesdev.mes.domain.core.dto.org.DepartmentMemberDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
@Named("orgBeanConvert")
public interface OrgBeanConvert {
    DepartmentDTO poToDto(DepartmentPO departmentPO);

    DepartmentMemberDTO poToDto(DepartmentMemberPO departmentMemberPO);
}
