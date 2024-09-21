package com.trionesdev.wms.rest.backend.domains.perm.controller.ro;

import com.trionesdev.wms.core.domains.perm.internal.enums.RoleGrantObjType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class RoleGrantRO {
    @NotNull
    private RoleGrantObjType grantObjType;
    @NotNull
    private List<String> grantObjIds;
}
