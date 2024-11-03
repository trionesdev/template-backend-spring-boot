package com.trionesdev.wms.rest.boss.domains.perm.controller.ro;

import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.wms.core.domains.perm.internal.enums.FunctionalResourceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FunctionalResourceCreateRO {

    private String appCode;
    private ClientType clientType;
    @NotBlank
    private String parentId;
    @NotNull
    private FunctionalResourceType type;
    private String groupCoe;
    @NotBlank
    private String name;
    @NotBlank
    private String uniqueCode;
    private String icon;
    private String description;
    private String apiCode;
    private String routePath;
}
