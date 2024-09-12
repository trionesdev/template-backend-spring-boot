package com.trionesdev.wms.rest.backend.domains.perm.internal;

import com.trionesdev.wms.core.domains.perm.dao.po.ViewResourceDraftPO;
import com.trionesdev.wms.rest.backend.domains.perm.controller.ro.ViewResourceDraftCreateRO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface PermBeRestConvert {
    ViewResourceDraftPO from(ViewResourceDraftCreateRO args);
}
