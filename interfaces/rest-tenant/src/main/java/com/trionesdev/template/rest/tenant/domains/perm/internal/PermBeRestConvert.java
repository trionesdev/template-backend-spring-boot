package com.trionesdev.template.rest.tenant.domains.perm.internal;

import com.trionesdev.template.core.domains.perm.dao.po.FunctionalResourceDraftPO;
import com.trionesdev.template.core.domains.perm.dao.po.RolePO;
import com.trionesdev.template.core.domains.perm.dto.PolicySaveCmd;
import com.trionesdev.template.core.domains.perm.dto.AddRoleGrantsCmd;
import com.trionesdev.template.core.domains.perm.dto.RemoveRoleGrantsCmd;
import com.trionesdev.template.rest.tenant.domains.perm.controller.ro.*;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface PermBeRestConvert {
    FunctionalResourceDraftPO from(FunctionalResourceDraftCreateRO args);

    FunctionalResourceDraftPO from(FunctionalResourceDraftUpdateRO args);

    RolePO from(RoleCreateRO args);

    RolePO from(RoleUpdateRO args);

    PolicySaveCmd from(PolicySaveRO args);

    AddRoleGrantsCmd from(RoleGrantRO args);

    RemoveRoleGrantsCmd from(RemoveRoleGrantRO args);
}
