package com.trionesdev.mes.domain.core.domains.manufacture.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
public class ManufactureOrder {
    private String id;
    private String code;
    private String productCode;
    private String remark;
    private BigDecimal planQuantity; //计划数量
    private Instant planStartTime;
    private Instant planEndTime;
    private Instant actualStartTime;
    private Instant actualEndTime;

    private List<Task> tasks;
    private List<Material> materials;


    @Data
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Task {
        private String processCode;
        private BigDecimal ratio;
        private BigDecimal planQuantity;
        private BigDecimal goodQuantity; //良品数量
        private BigDecimal defectiveQuantity; //不良品数量
        private Instant planStartTime;
        private Instant planEndTime;
        private Instant actualStartTime;
        private Instant actualEndTime;
        private List<Defective> defectives;
    }

    @Data
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Defective {
        private String code;
        private String name;
        private BigDecimal quantity;
    }

    @Data
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Material {
        private String productCode;
        private String processCode;
        private BigDecimal unitUsage; //单位用量
    }
}
