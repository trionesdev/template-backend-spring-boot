package com.trionesdev.wms.rest.backend.domains.org.internal;

import com.trionesdev.wms.core.domains.org.dao.criteria.DepartmentMemberCriteria;
import com.trionesdev.wms.core.domains.org.dao.criteria.TenantMemberCriteria;
import com.trionesdev.wms.core.domains.org.dao.po.DepartmentPO;
import com.trionesdev.wms.core.domains.org.dto.TenantMemberDetailDTO;
import com.trionesdev.wms.rest.backend.domains.org.controller.ro.*;
import com.trionesdev.wms.rest.backend.domains.org.controller.ro.department.DepartmentCreateRO;
import com.trionesdev.wms.rest.backend.domains.org.controller.ro.department.DepartmentMemberQueryRO;
import com.trionesdev.wms.rest.backend.domains.org.controller.ro.department.DepartmentUpdateRO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
@Named("orgBeRestBeanConvert")
public interface OrgBeRestConvert {

    //region tenant
    TenantMemberDetailDTO from(TenantMemberRO.Create args);

    TenantMemberDetailDTO from(TenantMemberRO.Update args);

    TenantMemberCriteria from(TenantMemberQueryRO query);
    //endregion


    //region department
    DepartmentPO from(DepartmentCreateRO arg);

    DepartmentPO from(DepartmentUpdateRO arg);

    DepartmentMemberCriteria from(DepartmentMemberQueryRO query);
    //endregion
}
