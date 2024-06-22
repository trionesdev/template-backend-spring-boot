package com.trionesdev.mes.rest.backend.domains.org.internal;

import com.trionesdev.mes.domain.core.domains.org.repository.criteria.DepartmentMemberCriteria;
import com.trionesdev.mes.domain.core.domains.org.repository.po.DepartmentPO;
import com.trionesdev.mes.rest.backend.domains.org.controller.query.DepartmentMemberQuery;
import com.trionesdev.mes.rest.backend.domains.org.controller.ro.DepartmentRO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
@Named("orgBeRestBeanConvert")
public interface OrgBeRestBeanConvert {

    DepartmentPO from(DepartmentRO.Create arg);

    DepartmentPO from(DepartmentRO.Update arg);

    DepartmentMemberCriteria from(DepartmentMemberQuery query);
}
