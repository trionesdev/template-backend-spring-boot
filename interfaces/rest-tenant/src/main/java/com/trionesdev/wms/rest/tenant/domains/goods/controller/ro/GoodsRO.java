package com.trionesdev.wms.rest.tenant.domains.goods.controller.ro;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoodsRO {
    private String code;
    private String name;
    private String unitCode;
    private String specification;
    private String mode;
    private BigDecimal weight;
    private BigDecimal volume;
    private Boolean enabled;
    private String remark;
}
