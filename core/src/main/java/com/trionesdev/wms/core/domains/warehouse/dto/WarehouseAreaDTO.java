package com.trionesdev.wms.core.domains.warehouse.dto;

import lombok.Data;

@Data
public class WarehouseAreaDTO {
    private String id;
    private String tenantId;
    private String warehouseId;
    private String code;
    private String name;
    private Boolean enabled;
    private String remark;

    private String warehouseName;
}
