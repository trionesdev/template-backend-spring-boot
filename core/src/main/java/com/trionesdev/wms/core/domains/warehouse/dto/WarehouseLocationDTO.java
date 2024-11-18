package com.trionesdev.wms.core.domains.warehouse.dto;

import lombok.Data;

@Data
public class WarehouseLocationDTO {
    private String id;
    private String tenantId;
    private String warehouseId;
    private String warehouseAreaId;
    private String code;
    private String name;
    private Integer floorQuantity;
    private Boolean enabled;
    private String remark;

    private String warehouseName;
    private String warehouseAreaName;
}
