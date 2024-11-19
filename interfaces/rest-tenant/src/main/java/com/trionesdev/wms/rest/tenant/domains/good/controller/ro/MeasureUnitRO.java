package com.trionesdev.wms.rest.tenant.domains.good.controller.ro;

import lombok.Data;

@Data
public class MeasureUnitRO {
    private String code;
    private String name;
    private Boolean enabled;
    private String remark;
}
