package com.trionesdev.template.rest.boss.domains.tenant.internal;

import com.trionesdev.template.core.domains.tenant.dao.criteria.TenantCriteria;
import com.trionesdev.template.core.domains.tenant.dto.cmd.BossUserCreateTenantCmd;
import com.trionesdev.template.rest.boss.domains.tenant.controller.ro.TenantCreateRO;
import com.trionesdev.template.rest.boss.domains.tenant.controller.ro.TenantQueryRO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface TenantRestBossConvert {
    TenantCriteria tenantCriteriaFromQueryRo(TenantQueryRO args);

    BossUserCreateTenantCmd bossUserCreateTenantCmdFromCreateRo(TenantCreateRO args);
}
