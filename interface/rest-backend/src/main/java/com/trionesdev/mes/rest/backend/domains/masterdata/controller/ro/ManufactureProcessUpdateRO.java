package com.trionesdev.mes.rest.backend.domains.masterdata.controller.ro;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ManufactureProcessUpdateRO {
    @NotBlank
    private String name;
}
