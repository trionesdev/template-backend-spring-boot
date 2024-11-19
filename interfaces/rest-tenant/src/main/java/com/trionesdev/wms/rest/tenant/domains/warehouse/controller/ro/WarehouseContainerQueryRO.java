package com.trionesdev.wms.rest.tenant.domains.warehouse.controller.ro;

import lombok.Data;

@Data
public class WarehouseContainerQueryRO {
    private String warehouseId;
    private String code;
    private String name;
    private Boolean enabled;
}
