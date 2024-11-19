package com.trionesdev.wms.rest.tenant.domains.good.controller.ro;

import lombok.Data;

@Data
public class GoodQueryRO {
    private String code;
    private String name;
    private Boolean enabled;
}
