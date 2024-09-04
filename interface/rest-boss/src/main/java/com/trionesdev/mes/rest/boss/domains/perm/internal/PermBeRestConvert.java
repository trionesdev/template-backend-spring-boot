package com.trionesdev.mes.rest.boss.domains.perm.internal;

import com.trionesdev.mes.core.domains.perm.dao.po.ResourceDraftPO;
import com.trionesdev.mes.rest.boss.domains.perm.controller.ro.ResourceDraftCreateRO;
import com.trionesdev.mes.rest.boss.domains.perm.controller.ro.ResourceDraftUpdateRO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface PermBeRestConvert {
    ResourceDraftPO from(ResourceDraftCreateRO args);

    ResourceDraftPO from(ResourceDraftUpdateRO args);
}
