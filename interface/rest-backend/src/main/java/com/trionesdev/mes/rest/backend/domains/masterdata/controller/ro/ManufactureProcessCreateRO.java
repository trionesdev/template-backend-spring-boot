package com.trionesdev.mes.rest.backend.domains.masterdata.controller.ro;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class ManufactureProcessCreateRO {
    private String code;
    @NotBlank
    private String name;
    private List<String> defectiveCodes;
}
