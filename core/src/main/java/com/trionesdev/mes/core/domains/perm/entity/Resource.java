package com.trionesdev.mes.core.domains.perm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Resource {
    private String id;
    private String appIdentifier;
    private String client;
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
