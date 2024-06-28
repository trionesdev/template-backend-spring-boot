package com.trionesdev.mes.core.domains.masterdata.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ManufactureBomDTO {
    private String id;
    private String code;
    private String productCode;
    private List<Material> materials;

    @Data
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Material {
        private String productCode;
        private BigDecimal unitUsage;
        private String processCode;
        private String remark;
    }
}
