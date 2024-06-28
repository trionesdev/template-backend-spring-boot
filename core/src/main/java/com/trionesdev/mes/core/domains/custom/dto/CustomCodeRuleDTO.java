package com.trionesdev.mes.core.domains.custom.dto;

import com.trionesdev.mes.core.domains.custom.internal.enums.TimeFormatTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CustomCodeRuleDTO {
    private String identifier;
    private String name;
    private String prefix;
    private TimeFormatTypeEnum timeFormatType;
    private Integer serialNumberDigits;
}
