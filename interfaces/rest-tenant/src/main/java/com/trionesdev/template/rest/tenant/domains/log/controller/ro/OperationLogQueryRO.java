package com.trionesdev.template.rest.tenant.domains.log.controller.ro;

import lombok.Data;

@Data
public class OperationLogQueryRO {
    private String type;
    private String category;
    private String action;
}
