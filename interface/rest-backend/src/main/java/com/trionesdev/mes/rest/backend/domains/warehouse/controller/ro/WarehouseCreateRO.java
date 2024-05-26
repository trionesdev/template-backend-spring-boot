package com.trionesdev.mes.rest.backend.domains.warehouse.controller.ro;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class WarehouseCreateRO {
    private String code;
    @NotBlank
    private String name;
    private String remark;
}
