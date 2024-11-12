package com.trionesdev.wms.core.domains.format.internal.aggregate.entity;

import cn.hutool.core.util.StrUtil;
import com.trionesdev.wms.core.domains.format.internal.enums.TimeFormatTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CodeFormatRule {
    private String identifier;
    private String name;
    private String prefix;
    private TimeFormatTypeEnum timeFormatType;
    private Integer serialNumberDigits;


    public String timeIdentifier() {
        Instant now = Instant.now();
        LocalDateTime dateTime = LocalDateTime.ofInstant(now, ZoneId.systemDefault());
        if (timeFormatType == null) {
            timeFormatType = TimeFormatTypeEnum.YYYY;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timeFormatType.getFormat());
        return dateTime.format(formatter);
    }


    public String generateCode(Integer serialNumber) {
        String timeIdentifier = timeIdentifier();
        return prefix + timeIdentifier + StrUtil.padPre(String.valueOf(serialNumber), serialNumberDigits, '0');
    }
}
