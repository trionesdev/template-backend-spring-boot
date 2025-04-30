package com.trionesdev.template.rest.boss.domains.boss.controller.ro.perm;

import com.trionesdev.template.core.domains.boss.shared.enums.ClientType;
import com.trionesdev.template.core.domains.boss.shared.enums.FunctionalResourceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BossFunctionalResourceDraftCreateRO {

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
