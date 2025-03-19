package com.trionesdev.template.core.domains.perm.internal;

import com.trionesdev.template.core.domains.perm.dao.po.FunctionalResourceDraftPO;
import com.trionesdev.template.core.domains.perm.dao.po.FunctionalResourcePO;
import com.trionesdev.template.core.domains.perm.dao.po.PermissionPO;
import com.trionesdev.template.core.domains.perm.dto.PermissionResourceDTO;
import com.trionesdev.template.core.domains.perm.shared.model.PermissionResource;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
public interface PermDomainConvert {

    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    FunctionalResourcePO resourceDraftToRelease(FunctionalResourceDraftPO draft);

    FunctionalResourceDraftPO resourceReleaseToDraft(FunctionalResourcePO draft);

    PermissionResourceDTO permissionEntityToDto(PermissionResource permission);

    PermissionResource permissionPoToPermissionResource(PermissionPO permission);
    PermissionPO permissionResourceToPermissionPo(PermissionResource  permission);
    PermissionPO permissionResourceDtoToPermissionPo(PermissionResourceDTO permission);
}
