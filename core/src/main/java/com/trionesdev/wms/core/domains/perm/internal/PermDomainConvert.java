package com.trionesdev.wms.core.domains.perm.internal;

import com.trionesdev.wms.core.domains.perm.dao.po.ViewResourceActionPO;
import com.trionesdev.wms.core.domains.perm.dao.po.ViewResourceDraftPO;
import com.trionesdev.wms.core.domains.perm.dao.po.ViewResourceObjectPO;
import com.trionesdev.wms.core.domains.perm.internal.aggregate.entity.Resource;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
public interface PermDomainConvert {

    Resource resourceDraftToEntity(ViewResourceDraftPO draft);

    ViewResourceObjectPO resourceDraftToPO(ViewResourceDraftPO draft);

    ViewResourceActionPO resourceDraftActionToPO(ViewResourceDraftPO.Action draft);

    ViewResourceObjectPO resourceObjectEntityToPo(Resource resource);

    ViewResourceActionPO resourceActionEntityToPo(Resource.Action resource);

    Resource resourceObjectToResource(ViewResourceObjectPO object);

    Resource.Action resourceActionToInnerAction(ViewResourceActionPO action);
}
