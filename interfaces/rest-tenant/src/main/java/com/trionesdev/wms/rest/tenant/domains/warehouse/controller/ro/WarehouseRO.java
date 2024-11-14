package com.trionesdev.wms.rest.tenant.domains.warehouse.controller.ro;

import lombok.Data;

@Data
public class WarehouseRO {
    private String code;
    private String name;
    private String address;
    private Boolean enabled;
    private String remark;
}
