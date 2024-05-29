package com.trionesdev.mes.rest.backend.domains.warehouse.controller.ro;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class WarehouseUpdateRO {
    @NotBlank
    private String name;
    private String remark;
}
