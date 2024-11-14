package com.trionesdev.wms.core.domains.base.dto;

import com.trionesdev.wms.core.domains.base.internal.enums.TimeFormatType;
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
    private TimeFormatType timeFormatType;
    private Integer serialNumberDigits;
}
