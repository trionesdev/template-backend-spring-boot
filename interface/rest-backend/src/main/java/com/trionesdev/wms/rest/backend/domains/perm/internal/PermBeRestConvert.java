package com.trionesdev.wms.rest.backend.domains.perm.internal;

import com.trionesdev.wms.core.domains.perm.dao.po.FunctionalResourceDraftPO;
import com.trionesdev.wms.core.domains.perm.dao.po.RolePO;
import com.trionesdev.wms.core.domains.perm.dto.PolicyDTO;
import com.trionesdev.wms.rest.backend.domains.perm.controller.ro.FunctionalResourceDraftCreateRO;
import com.trionesdev.wms.rest.backend.domains.perm.controller.ro.FunctionalResourceDraftUpdateRO;
import com.trionesdev.wms.rest.backend.domains.perm.controller.ro.PolicySaveRO;
import com.trionesdev.wms.rest.backend.domains.perm.controller.ro.RoleCreateRO;
import com.trionesdev.wms.rest.backend.domains.perm.controller.ro.RoleUpdateRO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface PermBeRestConvert {
    FunctionalResourceDraftPO from(FunctionalResourceDraftCreateRO args);

    FunctionalResourceDraftPO from(FunctionalResourceDraftUpdateRO args);

    RolePO from(RoleCreateRO args);

    RolePO from(RoleUpdateRO args);

    PolicyDTO from(PolicySaveRO args);
}
