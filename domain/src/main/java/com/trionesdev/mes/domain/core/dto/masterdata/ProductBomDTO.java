package com.trionesdev.mes.domain.core.dto.masterdata;

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
public class ProductBomDTO {
    private String code;
    private String name;
    private String specification;
    private String type;
    private UnitDTO unit;
    private List<Material> materials;

    @Data
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Material{
        private String materialCode;
        private BigDecimal unitUsage;
        private String processCode;
        private String remark;
    }
}
