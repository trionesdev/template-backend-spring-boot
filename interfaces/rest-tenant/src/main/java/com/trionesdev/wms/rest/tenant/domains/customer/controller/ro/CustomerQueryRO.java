package com.trionesdev.wms.rest.tenant.domains.customer.controller.ro;

import lombok.Data;

@Data
public class CustomerQueryRO {
    private String code;
    private String name;
    private String contactName;
    private Boolean enabled;
}
