package com.trionesdev.wms.rest.tenant.domains.supplier.controller.ro;

import lombok.Data;

@Data
public class SupplierRO {
    private String code;
    private String name;
    private String contactName;
    private String contactPhone;
    private String contactFixedTelephone;
    private String contactEmail;
    private String contactAddress;
    private Boolean enabled;
    private String remark;
}
