package com.trionesdev.template.rest.boss.domains.perm.controller.ro;

import com.trionesdev.template.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.template.core.domains.perm.internal.enums.FunctionalResourceType;
import lombok.Data;

@Data
public class FunctionalResourceUpdateRO {
    private String appCode;
    private ClientType clientType;
    private String parentId;
    private FunctionalResourceType type;
    private String groupCoe;
    private String name;
    private String uniqueCode;
    private String icon;
    private String description;
    private String apiCode;
    private String routePath;
}
