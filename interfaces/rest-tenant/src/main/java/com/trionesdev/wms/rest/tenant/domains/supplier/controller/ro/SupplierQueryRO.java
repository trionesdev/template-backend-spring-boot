package com.trionesdev.wms.rest.tenant.domains.supplier.controller.ro;

import lombok.Data;

@Data
public class SupplierQueryRO {
    private String code;
    private String name;
    private String contactName;
    private Boolean enabled;
}
