package com.trionesdev.template.rest.boss.domains.perm.controller.ro;

import com.trionesdev.template.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.template.core.domains.perm.internal.enums.FunctionalResourceType;
import lombok.Data;

import java.util.List;

@Data
public class FunctionalResourceDraftUpdateRO {
    private String parentId;
    private String appIdentifier;
    private ClientType clientType;
    private FunctionalResourceType type;
    private String name;
    private String identifier;
    private List<Action> actions;

    @Data
    public static class Action {
        private String name;
        private String identifier;
    }

}
