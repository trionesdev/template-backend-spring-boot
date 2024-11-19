package com.trionesdev.wms.rest.tenant.domains.customer.controller.ro;

import lombok.Data;

@Data
public class CustomerRO {
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
