package com.trionesdev.template.rest.tenant.domains.org.internal;

import com.trionesdev.template.core.domains.org.dao.criteria.DepartmentMemberCriteria;
import com.trionesdev.template.core.domains.org.dao.criteria.TenantMemberCriteria;
import com.trionesdev.template.core.domains.org.dao.po.DepartmentPO;
import com.trionesdev.template.core.domains.org.dto.*;
import com.trionesdev.template.rest.tenant.domains.org.controller.ro.department.DepartmentCreateRO;
import com.trionesdev.template.rest.tenant.domains.org.controller.ro.department.DepartmentMemberQueryRO;
import com.trionesdev.template.rest.tenant.domains.org.controller.ro.department.DepartmentUpdateRO;
import com.trionesdev.template.rest.tenant.domains.org.controller.ro.tenant.*;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
@Named("orgBeRestBeanConvert")
public interface OrgBeRestConvert {

    //region tenant
    TenantMemberCreateCmd from(TenantMemberCreateRO args);

    TenantMemberProfileUpdateCmd from(TenantMemberUpdateRO args);

    TenantMemberUpdateCmd from(ActorMemberProfileUpdateRO args);

    ActorChangePasswordCmd form(ActorMemberChangePasswordRO args);

    ChangePasswordCmd form(ChangePasswordRO args);

    TenantMemberCriteria from(TenantMemberQueryRO query);
    //endregion


    //region department
    DepartmentPO from(DepartmentCreateRO arg);

    DepartmentPO from(DepartmentUpdateRO arg);

    DepartmentMemberCriteria from(DepartmentMemberQueryRO query);
    //endregion
}
