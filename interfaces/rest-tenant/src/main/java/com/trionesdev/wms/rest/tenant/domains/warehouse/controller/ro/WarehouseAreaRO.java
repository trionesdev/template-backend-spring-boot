package com.trionesdev.wms.rest.tenant.domains.warehouse.controller.ro;

import lombok.Data;

@Data
public class WarehouseAreaRO {
    private String warehouseId;
    private String code;
    private String name;
    private Boolean enabled;
    private String remark;
}
