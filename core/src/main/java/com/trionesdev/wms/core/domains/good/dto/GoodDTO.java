package com.trionesdev.wms.core.domains.good.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoodDTO {
    private String id;
    private String tenantId;
    private String code;
    private String name;
    private String unitCode;
    private String specification;
    private String mode;
    private BigDecimal weight;
    private BigDecimal volume;
    private Boolean enabled;
    private String remark;

    private String measureUnit;
}
