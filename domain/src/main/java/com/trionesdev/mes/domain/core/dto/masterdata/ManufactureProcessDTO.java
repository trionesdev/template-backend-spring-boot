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
public class ManufactureProcessDTO {
    private String id;
    private String code;
    private String name;
    private BigDecimal ratio;
    private List<String> defectiveCodes;
    private List<Defective> defectives;

    @Data
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Defective {
        private String code;
        private String name;
    }
}
