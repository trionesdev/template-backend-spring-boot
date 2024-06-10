package com.trionesdev.mes.domain.core.dto.manufacture;

import com.trionesdev.mes.domain.core.dto.masterdata.DefectiveDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
public class ManufactureOrderDTO {
    private String id;
    private String code;
    private String productCode;
    private String remark;
    private BigDecimal planQuantity; //计划数量
    private Instant planStartTime;
    private Instant planEndTime;
    private Instant actualStartTime;
    private Instant actualEndTime;
    private Product product;
    private List<Task> tasks;
    private List<Material> materials;

    @Data
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Product {
        private String code;
        private String name;
        private String specification;
    }


    @Data
    @Accessors(chain = true)
    public static class Task {
        private String processCode;
        private String name;
        private BigDecimal ratio;
        private BigDecimal planQuantity;
        private BigDecimal goodQuantity;
        private BigDecimal defectiveQuantity;
        private List<DefectiveDTO> defectives;
        private Instant planStartTime;
        private Instant planEndTime;
        private Instant actualStartTime;
        private Instant actualEndTime;
    }

    @Data
    @Accessors(chain = true)
    public static class Material {
        private String productCode;
        private BigDecimal unitUsage;
        private String remark;
        private String name;
        private String specification;
        private String type;
        private BigDecimal inventory;
        private String unit;
    }
}
