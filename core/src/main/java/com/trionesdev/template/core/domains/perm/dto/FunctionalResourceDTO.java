package com.trionesdev.template.core.domains.perm.dto;

import com.trionesdev.template.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.template.core.domains.perm.internal.enums.FunctionalResourceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class FunctionalResourceDTO {
    private String id;
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
