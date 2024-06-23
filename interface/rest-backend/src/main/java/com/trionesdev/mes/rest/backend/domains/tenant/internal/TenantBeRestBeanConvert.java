package com.trionesdev.mes.rest.backend.domains.tenant.internal;

import com.trionesdev.mes.domain.core.domains.tenant.entity.TenantMember;
import com.trionesdev.mes.domain.core.domains.tenant.repository.criteria.TenantMemberCriteria;
import com.trionesdev.mes.rest.backend.domains.tenant.controller.query.TenantMemberQuery;
import com.trionesdev.mes.rest.backend.domains.tenant.controller.ro.TenantMemberRO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
@Named("tenantBeRestBeanConvert")
public interface TenantBeRestBeanConvert {

    TenantMember from(TenantMemberRO.Create args);

    TenantMember from(TenantMemberRO.Update args);

    TenantMemberCriteria from(TenantMemberQuery query);
}
