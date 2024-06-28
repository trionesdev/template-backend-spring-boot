package com.trionesdev.mes.core.domains.manufacture.dto;

import com.trionesdev.mes.core.domains.masterdata.dto.DefectiveDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@Accessors(chain = true)
public class ManufactureOrderTaskDTO {
    private String id;
    private String orderCode;
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
    private Product product;

    @Data
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Product {
        private String code;
        private String name;
        private String specification;
    }
}
