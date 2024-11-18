package com.trionesdev.wms.rest.tenant.domains.warehouse.controller.ro;

import lombok.Data;

@Data
public class WarehouseLocationRO {
    private String warehouseId;
    private String warehouseAreaId;
    private String code;
    private String name;
    private Integer floorQuantity;
    private Boolean enabled;
    private String remark;
}
