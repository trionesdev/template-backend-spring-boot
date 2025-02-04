package com.trionesdev.template.core.domains.perm.internal;

import com.trionesdev.template.core.domains.perm.dao.po.FunctionalResourceActionPO;
import com.trionesdev.template.core.domains.perm.dao.po.FunctionalResourceDraftPO;
import com.trionesdev.template.core.domains.perm.dao.po.FunctionalResourceObjectPO;
import com.trionesdev.template.core.domains.perm.dao.po.FunctionalResourcePO;
import com.trionesdev.template.core.domains.perm.dto.FunctionalResourceDTO;
import com.trionesdev.template.core.domains.perm.dto.PermissionDTO;
import com.trionesdev.template.core.domains.perm.dto.PolicySaveCmd;
import com.trionesdev.template.core.domains.perm.internal.aggregate.entity.FunctionalResource;
import com.trionesdev.template.core.domains.perm.internal.aggregate.entity.Permission;
import com.trionesdev.template.core.domains.perm.internal.aggregate.entity.Policy;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
public interface PermDomainConvert {

    FunctionalResourcePO resourceEntityToPo(FunctionalResource resource);

    FunctionalResource resourcePoToEntity(FunctionalResourcePO po);

    FunctionalResourceDTO resourceEntityToDto(FunctionalResource resource);





    FunctionalResource resourceDraftToEntity(FunctionalResourceDraftPO draft);

    FunctionalResourceObjectPO resourceDraftToPO(FunctionalResourceDraftPO draft);

    FunctionalResourceActionPO resourceDraftActionToPO(FunctionalResourceDraftPO.Action draft);

    FunctionalResourceObjectPO resourceObjectEntityToPo(FunctionalResource resource);

    FunctionalResourceActionPO resourceActionEntityToPo(FunctionalResource.Action resource);

    FunctionalResource resourceObjectToResource(FunctionalResourceObjectPO object);

    FunctionalResource.Action resourceActionToInnerAction(FunctionalResourceActionPO action);


    Policy policyDtoToEntity(PolicySaveCmd dto);

    PermissionDTO permissionEntityToDto(Permission permission);
}
