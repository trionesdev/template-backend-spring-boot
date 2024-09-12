package com.trionesdev.wms.core.domains.perm.internal;

import com.trionesdev.wms.core.domains.perm.dao.po.ResourceActionPO;
import com.trionesdev.wms.core.domains.perm.dao.po.ResourceDraftPO;
import com.trionesdev.wms.core.domains.perm.dao.po.ResourceObjectPO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
@Named("permBeanConvert")
public interface PermBeanConvert {

    ResourceObjectPO resourceDraftToPO(ResourceDraftPO draft);

    ResourceActionPO resourceDraftActionToPO(ResourceDraftPO.Action draft);
}
