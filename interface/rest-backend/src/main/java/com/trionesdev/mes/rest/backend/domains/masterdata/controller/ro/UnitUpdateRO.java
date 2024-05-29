package com.trionesdev.mes.rest.backend.domains.masterdata.controller.ro;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UnitUpdateRO {
    @NotBlank
    private String name;
    private String remark;
}
