package com.trionesdev.wms.core.domains.format.dto;

import com.trionesdev.wms.core.domains.format.internal.enums.TimeFormatTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CodeFormatRuleDTO {
    private String identifier;
    private String name;
    private String prefix;
    private TimeFormatTypeEnum timeFormatType;
    private Integer serialNumberDigits;
}
