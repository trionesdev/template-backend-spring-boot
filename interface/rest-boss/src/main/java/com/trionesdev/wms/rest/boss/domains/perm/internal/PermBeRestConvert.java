package com.trionesdev.wms.rest.boss.domains.perm.internal;

import com.trionesdev.wms.core.domains.perm.dao.po.FunctionalResourceDraftPO;
import com.trionesdev.wms.rest.boss.domains.perm.controller.ro.ResourceDraftCreateRO;
import com.trionesdev.wms.rest.boss.domains.perm.controller.ro.ResourceDraftUpdateRO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface PermBeRestConvert {
    FunctionalResourceDraftPO from(ResourceDraftCreateRO args);

    FunctionalResourceDraftPO from(ResourceDraftUpdateRO args);
}
