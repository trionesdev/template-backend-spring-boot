package com.trionesdev.template.core.domains.boss.internal;

import com.trionesdev.template.core.domains.boss.dao.po.BossFunctionalResourceDraftPO;
import com.trionesdev.template.core.domains.boss.dao.po.BossFunctionalResourcePO;
import com.trionesdev.template.core.domains.boss.dao.po.BossPermissionPO;
import com.trionesdev.template.core.domains.boss.dto.perm.BossPermissionResourceDTO;
import com.trionesdev.template.core.domains.boss.shared.model.BossPermissionResource;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
public interface BossPermDomainConvert {

    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    BossFunctionalResourcePO resourceDraftToRelease(BossFunctionalResourceDraftPO draft);

    BossFunctionalResourceDraftPO resourceReleaseToDraft(BossFunctionalResourcePO draft);
    BossPermissionResource permissionPoToPermissionResource(BossPermissionPO permission);

    BossPermissionResourceDTO permissionEntityToDto(BossPermissionResource permission);


    BossPermissionPO permissionResourceToPermissionPo(BossPermissionResource permission);
}
