package com.trionesdev.wms.core.domains.custom.internal.aggregate.entity;

import cn.hutool.core.util.StrUtil;
import com.trionesdev.wms.core.domains.custom.internal.enums.TimeFormatTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CustomCodeRule {
    private String identifier;
    private String name;
    private String prefix;
    private TimeFormatTypeEnum timeFormatType;
    private Integer serialNumberDigits;


    public String timeIdentifier() {
        Instant now = Instant.now();
        LocalDateTime dateTime = LocalDateTime.ofInstant(now, ZoneId.systemDefault());
        switch (timeFormatType) {
            case YYYY:
                return String.valueOf(dateTime.getYear());
            case YYYY_MM:
                return String.valueOf(dateTime.getYear()) + dateTime.getMonthValue();
            case YYYY_MM_DD:
                return String.valueOf(dateTime.getYear()) + dateTime.getMonthValue() + dateTime.getDayOfMonth();
            default:
                return null;
        }
    }


    public String generateCode(Integer serialNumber) {
        String timeIdentifier = timeIdentifier();
        return prefix + timeIdentifier + StrUtil.padPre(String.valueOf(serialNumber), serialNumberDigits, '0');
    }
}
