package com.trionesdev.mes.rest.backend.domains.masterdata.controller.ro;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class ManufactureProcessUpdateRO {
    @NotBlank
    private String name;
    private List<String> defectiveCodes;
}
