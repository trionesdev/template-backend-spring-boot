package com.trionesdev.wms.rest.tenant.domains.warehouse.controller.ro;

import lombok.Data;

@Data
public class WarehouseQueryRO {
    private String code;
    private String name;
    private Boolean enabled;
}
