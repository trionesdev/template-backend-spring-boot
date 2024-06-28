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
public class ProductMaterialDTO {
    private String materialCode;
    private BigDecimal unitUsage;
    private String processCode;
    private String remark;
    private Product product;
    private Unit unit;
    private Process process;

    @Data
    public static class Product{
        private String code;
        private String name;
    }

    @Data
    public static class Unit {
        private String id;
        private String name;
    }

    @Data
    public static class Process {
        private String code;
        private String name;
    }
}
