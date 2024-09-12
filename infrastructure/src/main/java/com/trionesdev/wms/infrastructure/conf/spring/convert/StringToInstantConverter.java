package com.trionesdev.wms.infrastructure.conf.spring.convert;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

import java.time.Instant;

public class StringToInstantConverter implements Converter<String, Instant> {

    @Override
    public Instant convert(String source) {
        if(StringUtils.isBlank(source)) {
            return null;
        }

        Long val = Long.valueOf(source);
        return Instant.ofEpochMilli(val);
    }
}
