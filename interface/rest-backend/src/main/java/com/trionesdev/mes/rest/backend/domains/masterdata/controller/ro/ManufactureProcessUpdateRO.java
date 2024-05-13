package com.trionesdev.mes.rest.backend.domains.masterdata.controller.ro;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ManufactureProcessUpdateRO {
    @NotBlank
    private String name;
}
