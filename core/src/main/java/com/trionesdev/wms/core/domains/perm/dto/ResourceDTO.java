package com.trionesdev.wms.core.domains.perm.dto;

import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ResourceDTO {
    private String id;
    private ClientType clientType;
    private String parentId;
    private String name;
    private String identifier;
    private String type;
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
