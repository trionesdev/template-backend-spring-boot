package com.trionesdev.mes.rest.backend.domains.org.internal;

import com.trionesdev.mes.core.domains.org.dao.criteria.DepartmentMemberCriteria;
import com.trionesdev.mes.core.domains.org.dao.criteria.TenantMemberCriteria;
import com.trionesdev.mes.core.domains.org.dao.po.DepartmentPO;
import com.trionesdev.mes.core.domains.org.dto.TenantMemberDTO;
import com.trionesdev.mes.rest.backend.domains.org.controller.query.DepartmentMemberQuery;
import com.trionesdev.mes.rest.backend.domains.org.controller.query.TenantMemberQuery;
import com.trionesdev.mes.rest.backend.domains.org.controller.ro.DepartmentRO;
import com.trionesdev.mes.rest.backend.domains.org.controller.ro.TenantMemberRO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
@Named("orgBeRestBeanConvert")
public interface OrgBeRestBeanConvert {

    //region tenant
    TenantMemberDTO from(TenantMemberRO.Create args);

    TenantMemberDTO from(TenantMemberRO.Update args);

    TenantMemberCriteria from(TenantMemberQuery query);
    //endregion


    //region department
    DepartmentPO from(DepartmentRO.Create arg);

    DepartmentPO from(DepartmentRO.Update arg);

    DepartmentMemberCriteria from(DepartmentMemberQuery query);
    //endregion
}
