package com.trionesdev.wms.rest.tenant.domains.goods.controller.ro;

import lombok.Data;

@Data
public class MeasureUnitQueryRO {
    private String code;
    private String name;
    private Boolean enabled;
}
