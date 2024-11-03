package com.trionesdev.wms.rest.tenant.domains.perm.controller.ro;

import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.wms.core.domains.perm.internal.enums.FunctionalResourceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
public class FunctionalResourceDraftCreateRO {
    private String parentId;
    private ClientType clientType;
    private FunctionalResourceType type;
    private String name;
    private String identifier;
    private List<Action> actions;

    @Data
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Action {
        private String name;
        private String identifier;
    }
}
