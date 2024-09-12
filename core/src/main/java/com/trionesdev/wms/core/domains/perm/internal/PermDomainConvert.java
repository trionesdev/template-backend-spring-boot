package com.trionesdev.wms.core.domains.perm.internal;

import com.trionesdev.wms.core.domains.perm.dao.po.ResourceActionPO;
import com.trionesdev.wms.core.domains.perm.dao.po.ResourceDraftPO;
import com.trionesdev.wms.core.domains.perm.dao.po.ResourceObjectPO;
import com.trionesdev.wms.core.domains.perm.internal.aggregate.entity.Resource;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
public interface PermDomainConvert {

    Resource resourceDraftToEntity(ResourceDraftPO draft);

    ResourceObjectPO resourceDraftToPO(ResourceDraftPO draft);

    ResourceActionPO resourceDraftActionToPO(ResourceDraftPO.Action draft);

    ResourceObjectPO resourceObjectEntityToPo(Resource resource);

    ResourceActionPO resourceActionEntityToPo(Resource.Action resource);

    Resource resourceObjectToResource(ResourceObjectPO object);

    Resource.Action resourceActionToInnerAction(ResourceActionPO action);
}
