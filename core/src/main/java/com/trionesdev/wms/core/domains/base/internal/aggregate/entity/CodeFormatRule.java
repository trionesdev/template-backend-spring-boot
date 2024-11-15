package com.trionesdev.wms.core.domains.base.internal.aggregate.entity;

import cn.hutool.core.util.StrUtil;
import com.trionesdev.commons.exception.DuplicatedException;
import com.trionesdev.commons.exception.TrionesError;
import com.trionesdev.commons.exception.ValidationException;
import com.trionesdev.wms.core.domains.base.internal.enums.TimeFormatType;
import com.trionesdev.wms.core.domains.base.repository.impl.CodeFormatRepository;
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
    private String id;
    private String name;
    private String identifier;
    private String prefix;
    private TimeFormatType timeFormatType;
    private Integer serialNumberDigits;
    private String description;

    public String timeIdentifier() {
        Instant now = Instant.now();
        LocalDateTime dateTime = LocalDateTime.ofInstant(now, ZoneId.systemDefault());
        if (timeFormatType == null) {
            timeFormatType = TimeFormatType.YYYY;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timeFormatType.getFormat());
        return dateTime.format(formatter);
    }


    public String generateCode(Integer serialNumber) {
        String timeIdentifier = timeIdentifier();
        return prefix + timeIdentifier + StrUtil.padPre(String.valueOf(serialNumber), serialNumberDigits, '0');
    }

    public void uniqueValidate(CodeFormatRepository repository) {
        if (StrUtil.isBlank(identifier)) {
            throw new ValidationException(TrionesError.builder().message("identifier can not be empty").build());
        }
        repository.findByIdentifier(identifier).filter(entity -> !entity.getId().equals(id)).ifPresent(entity -> {
            throw new DuplicatedException(TrionesError.builder().message("identifier duplicated").build());
        });
    }

}
