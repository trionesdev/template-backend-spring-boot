package com.trionesdev.template.core.domains.base.internal.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TimeFormatType {
    YYYY("yyyy"),
    YYYY_MM("yyyyMM"),
    YYYY_MM_DD("yyyyMMdd"),
    YYYY_MM_DD_HH("yyyyMMddHH"),
    YYYY_MM_DD_HH_MM("yyyyMMddHHmm"),
    YYYY_MM_DD_HH_MM_SS("yyyyMMddHHmmss");
    private final String format;
}
