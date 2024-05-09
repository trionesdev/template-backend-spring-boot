package com.trionesdev.mes.rest.backend.domains.masterdata.controller.ro;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UnitUpdateRO {
    @NotBlank
    private String name;
    private String remark;
}
