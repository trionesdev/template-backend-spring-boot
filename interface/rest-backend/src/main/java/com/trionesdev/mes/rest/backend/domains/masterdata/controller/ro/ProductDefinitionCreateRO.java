package com.trionesdev.mes.rest.backend.domains.masterdata.controller.ro;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class ProductDefinitionCreateRO {
    private String code;
    @NotBlank
    private String name;
    private String unit;
    private String specification;
    @NotBlank
    private String type;
    private String technologicalCode; //工艺路线
    private String defaultSupplierCode;
    private Integer maxInventory;
    private Integer minInventory;
    private BigDecimal unitCost; //成本单价
    private BigDecimal unitPrice; //销售单价
}
