package com.trionesdev.mes.rest.backend.domains.masterdata.controller.ro;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ManufactureProcessCreateRO {
    private String code;
    @NotBlank
    private String name;
    private BigDecimal ratio;
    private List<String> defectiveCodes;
}
