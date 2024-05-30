package com.trionesdev.mes.rest.backend.domains.masterdata.controller.ro;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductDefinitionUpdateRO {
    @NotBlank
    private String name;
    private String unitId;
    private String specification;
    @NotBlank
    private String type;
    private String processFlowCode; //工艺路线
    private String defaultSupplierCode;
    private Integer maxInventory;
    private Integer minInventory;
    private BigDecimal unitCost; //成本单价
    private BigDecimal unitPrice; //销售单价
}
