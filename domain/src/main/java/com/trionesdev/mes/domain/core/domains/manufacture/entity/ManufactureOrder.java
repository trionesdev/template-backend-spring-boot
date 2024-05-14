package com.trionesdev.mes.domain.core.domains.manufacture.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
public class ManufactureOrder {
    private String id;
    private String code;
    private String productCode;
    private List<Task> tasks;
    private List<Material> materials;


    @Data
    public static class Task {
        private String processCode;
        private BigDecimal planQuantity;
        private BigDecimal goodQuantity;
        private BigDecimal defectiveQuantity;
    }

    @Data
    public static class Material {
        private String productCode;
        private BigDecimal unitUsage;
    }
}
