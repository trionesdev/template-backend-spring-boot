package com.trionesdev.mes.rest.backend.domains.manufacture.controller.query;

import lombok.Data;

@Data
public class ManufactureOrderTaskQuery {
    private String processCode;;
    private String orderCode;
    private String orderStatus;
}
