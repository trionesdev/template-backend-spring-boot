package com.trionesdev.mes.domain.core.dto.manufacture;

import com.trionesdev.mes.domain.core.dto.masterdata.DefectiveDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
public class ManufactureOrderDTO {
    private String id;
    private String code;
    private String productCode;
    private List<Task> tasks;
    private List<Material> materials;


    @Data
    public static class Task {
        private String processCode;
        private String name;
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
    public static class Material {
        private String productCode;
        private BigDecimal unitUsage;
    }
}
