package com.trionesdev.template.rest.tenant.domains.perm.controller.ro;

import com.trionesdev.template.core.domains.perm.internal.enums.RoleSubjectType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class RoleGrantRO {
    @NotNull
    private RoleSubjectType grantObjType;
    @NotNull
    private List<String> grantObjIds;
}
