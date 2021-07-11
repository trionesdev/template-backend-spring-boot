package com.ms.core.commons.ctx;

import lombok.Data;

import java.time.Instant;

@Data
public class Operator {
    private OperatorRoleEnum operatorRole;
    private Object operatorId;
    private String tenantId;
    private Instant time;
}
