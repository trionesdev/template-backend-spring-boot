package com.trionesdev.mes.rest.backend.domains.manufacture.controller.ro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class ManufactureOrderCreateRO {
    private String code;
    @NotBlank
    private String productCode;
    private String remark;
    @NotNull
    private BigDecimal planQuantity; //计划数量
    private Instant planStartTime;
    private Instant planEndTime;
    private Instant actualStartTime;
    private Instant actualEndTime;
}
