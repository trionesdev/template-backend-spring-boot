package com.trionesdev.mes.rest.backend.domains.manufacture.controller.ro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
public class ManufactureOrderCreateRO {
    private String code;
    @NotBlank
    private String productCode;
    private String remark;
    @NotNull
    private BigDecimal planQuantity; //计划数量
    private Instant planStartTime;
    private Instant planEndTime;
    private List<Task> tasks;
    private List<Material> materials;

    @Data
    public static class Task {
        private String processCode;
        private BigDecimal ratio;
        private BigDecimal planQuantity;
        private Instant planStartTime;
        private Instant planEndTime;
    }

    @Data
    public static class Material {
        private String materialCode;
        private String processCode;
        private BigDecimal unitUsage; //单位用量
        private String remark;
    }
}
