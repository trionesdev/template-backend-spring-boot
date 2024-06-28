package com.trionesdev.mes.core.domains.masterdata.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDefinitionDTO {
    private String id;
    private String code;
    private String name;
    private String unitId;
    private String specification;
    private String type;
    private String processFlowCode; //工艺路线
    private String defaultSupplierCode;
    private Integer maxInventory;
    private Integer minInventory;
    private BigDecimal unitCost; //成本单价
    private BigDecimal unitPrice; //销售单价
    private BigDecimal inventory = BigDecimal.ZERO;
    private Unit unit;
    private ProcessFlow processFlow;
    private Supplier defaultSupplier;


    @Data
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Unit {
        private String id;
        private String name;
    }

    @Data
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProcessFlow {
        private String code;
        private String name;
    }

    @Data
    @SuperBuilder
    public static class Supplier {
        private String code;
        private String name;
    }
}
