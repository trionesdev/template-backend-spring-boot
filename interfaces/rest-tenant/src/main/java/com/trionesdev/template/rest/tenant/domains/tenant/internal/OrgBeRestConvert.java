package com.trionesdev.template.rest.tenant.domains.tenant.internal;

import com.trionesdev.template.core.domains.tenant.dao.criteria.DepartmentMemberCriteria;
import com.trionesdev.template.core.domains.tenant.dao.criteria.TenantMemberCriteria;
import com.trionesdev.template.core.domains.tenant.dao.po.DepartmentPO;
import com.trionesdev.template.core.domains.tenant.dao.po.TenantPO;
import com.trionesdev.template.core.domains.tenant.dto.cmd.*;
import com.trionesdev.template.rest.tenant.domains.tenant.controller.ro.department.DepartmentCreateRO;
import com.trionesdev.template.rest.tenant.domains.tenant.controller.ro.department.DepartmentMemberQueryRO;
import com.trionesdev.template.rest.tenant.domains.tenant.controller.ro.department.DepartmentUpdateRO;
import com.trionesdev.template.rest.tenant.domains.tenant.controller.ro.tenant.*;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
@Named("orgBeRestBeanConvert")
public interface OrgBeRestConvert {

    //region tenant
    TenantPO from(TenantCreateRO args);

    TenantPO from(TenantUpdateRO args);
    //endregion

    //region tenant member
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
