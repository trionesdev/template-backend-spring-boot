package com.trionesdev.wms.rest.boss.domains.perm.controller.ro;

import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.wms.core.domains.perm.internal.enums.ResourceType;
import lombok.Data;

import java.util.List;

@Data
public class ResourceDraftUpdateRO {
    private String parentId;
    private ClientType clientType;
    private ResourceType type;
    private String name;
    private String identifier;
    private List<Action> actions;

    @Data
    public static class Action {
        private String name;
        private String identifier;
    }

}
