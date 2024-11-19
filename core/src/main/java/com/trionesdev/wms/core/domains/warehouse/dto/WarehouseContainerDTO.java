package com.trionesdev.wms.core.domains.warehouse.dto;

import com.trionesdev.wms.core.domains.warehouse.dao.po.enums.WarehouseContainerStatusEnum;
import lombok.Data;

@Data
public class WarehouseContainerDTO {
    private String id;
    private String tenantId;
    private String warehouseId;
    private String code;
    private String name;
    private String type;
    private WarehouseContainerStatusEnum status;
    private Boolean enabled;
    private String remark;

    private String warehouseName;
}
