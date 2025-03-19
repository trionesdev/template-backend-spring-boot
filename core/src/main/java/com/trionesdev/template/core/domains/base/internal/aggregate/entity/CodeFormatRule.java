package com.trionesdev.template.core.domains.base.internal.aggregate.entity;

import cn.hutool.core.util.StrUtil;
import com.trionesdev.commons.exception.DuplicatedException;
import com.trionesdev.commons.exception.TrionesError;
import com.trionesdev.commons.exception.ValidationException;
import com.trionesdev.template.core.domains.base.repository.impl.CodeFormatRepository;
import com.trionesdev.template.core.domains.base.shared.enums.TimeFormatType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.trionesdev.template.core.domains.base.internal.BaseConstants.DEFAULT_CODE_FORMAT_RULES;

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

    public CodeFormatRule mergeDefault() {
       return DEFAULT_CODE_FORMAT_RULES.stream().filter(rule -> !rule.getIdentifier().equals(identifier)).findFirst().map(rule->{
           rule.setTimeFormatType(timeFormatType);
           rule.setPrefix(prefix);
           rule.setSerialNumberDigits(serialNumberDigits);
           rule.setDescription(description);
           return rule;
       }).orElse(null);
    }

    public CodeFormatRule mergeDefault(CodeFormatRule defaultRule) {
        defaultRule.setTimeFormatType(timeFormatType);
        defaultRule.setPrefix(prefix);
        defaultRule.setSerialNumberDigits(serialNumberDigits);
        defaultRule.setDescription(description);
        return defaultRule;
    }

    /**
     * 默认规则  合并 数据库规则
     * @param rules
     * @return
     */
    public CodeFormatRule merge(List<CodeFormatRule> rules) {
        return rules.stream().filter(rule -> rule.getIdentifier().equals(identifier))
                .findFirst().map(matchRule->{
                    matchRule.setIdentifier(identifier);
                    matchRule.setName(name);
                    return matchRule;
                }).orElse(this);
    }
}
