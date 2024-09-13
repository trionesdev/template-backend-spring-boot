package com.trionesdev.wms.core.domains.perm.internal;

import com.trionesdev.wms.core.domains.perm.dao.po.FunctionalResourceActionPO;
import com.trionesdev.wms.core.domains.perm.dao.po.FunctionalResourceDraftPO;
import com.trionesdev.wms.core.domains.perm.dao.po.FunctionalResourceObjectPO;
import com.trionesdev.wms.core.domains.perm.internal.aggregate.entity.FunctionalResource;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
public interface PermDomainConvert {

    FunctionalResource resourceDraftToEntity(FunctionalResourceDraftPO draft);

    FunctionalResourceObjectPO resourceDraftToPO(FunctionalResourceDraftPO draft);

    FunctionalResourceActionPO resourceDraftActionToPO(FunctionalResourceDraftPO.Action draft);

    FunctionalResourceObjectPO resourceObjectEntityToPo(FunctionalResource resource);

    FunctionalResourceActionPO resourceActionEntityToPo(FunctionalResource.Action resource);

    FunctionalResource resourceObjectToResource(FunctionalResourceObjectPO object);

    FunctionalResource.Action resourceActionToInnerAction(FunctionalResourceActionPO action);
}
