package com.trionesdev.mes.domain.core.domains.manufacture.entity;

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
    public static class Task {
        private String processCode;
        private BigDecimal ratio; //配比
        private BigDecimal planQuantity;
        private Instant planStartTime;
        private Instant planEndTime;
    }

    @Data
    public static class Material {
        private String productCode;
        private BigDecimal unitUsage; //单位用量
    }
}
