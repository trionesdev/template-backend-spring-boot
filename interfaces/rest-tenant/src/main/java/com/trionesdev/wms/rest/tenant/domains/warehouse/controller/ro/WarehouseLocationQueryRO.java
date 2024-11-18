package com.trionesdev.wms.rest.tenant.domains.warehouse.controller.ro;

import lombok.Data;

@Data
public class WarehouseLocationQueryRO {
    private String warehouseId;
    private String warehouseAreaId;
    private String code;
    private String name;
    private Boolean enabled;
}
