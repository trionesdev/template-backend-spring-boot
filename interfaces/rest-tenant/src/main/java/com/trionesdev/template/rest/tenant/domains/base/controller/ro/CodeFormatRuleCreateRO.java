package com.trionesdev.template.rest.tenant.domains.base.controller.ro;

import com.trionesdev.template.core.domains.base.internal.enums.TimeFormatType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CodeFormatRuleCreateRO {
    @NotBlank
    private String name;
    @NotBlank
    private String identifier;
    @NotBlank
    private String prefix;
    @NotNull
    private TimeFormatType timeFormatType;
    @NotNull
    private Integer serialNumberDigits;
}
