package com.trionesdev.template.core.domains.log.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.Map;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class OperationLogExtDTO {
    private String id;
    private String tenantId;
    private String actorId;
    private String actorRole;
    private String objId;
    private String type;
    private String category;
    private String action;
    private String description;
    private Instant startAt;
    private Instant endAt;
    private Boolean success;
    private String errorMsg;
    private String beforeValues;
    private String afterValues;
    private Map<String, String> extra;
    private Operator operator;

    @Data
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Operator {
        private String username;
        private String nickname;
    }
}
