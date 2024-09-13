package com.trionesdev.wms.rest.boss.domains.perm.internal;

import com.trionesdev.wms.core.domains.perm.dao.po.FunctionalResourceDraftPO;
import com.trionesdev.wms.rest.boss.domains.perm.controller.ro.FunctionalResourceDraftCreateRO;
import com.trionesdev.wms.rest.boss.domains.perm.controller.ro.FunctionalResourceDraftUpdateRO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface PermBossRestConvert {
    FunctionalResourceDraftPO from(FunctionalResourceDraftCreateRO args);

    FunctionalResourceDraftPO from(FunctionalResourceDraftUpdateRO args);
}
