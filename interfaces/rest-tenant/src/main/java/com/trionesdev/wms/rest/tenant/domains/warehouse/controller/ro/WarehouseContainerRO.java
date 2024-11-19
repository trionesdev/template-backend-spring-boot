package com.trionesdev.wms.rest.tenant.domains.warehouse.controller.ro;

import com.trionesdev.wms.core.domains.warehouse.dao.po.enums.WarehouseContainerStatusEnum;
import lombok.Data;

@Data
public class WarehouseContainerRO {
    private String warehouseId;
    private String code;
    private String name;
    private String type;
    private WarehouseContainerStatusEnum status;
    private Boolean enabled;
    private String remark;
}