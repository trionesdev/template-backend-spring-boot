package com.trionesdev.template.core.domains.base.manager.impl;

import com.trionesdev.commons.context.actor.ActorContext;
import com.trionesdev.spring.lock.Lock;
import com.trionesdev.template.core.domains.base.internal.aggregate.entity.CodeFormatRule;
import com.trionesdev.template.core.domains.base.repository.impl.CodeFormatRepository;
import com.trionesdev.template.core.domains.base.shared.enums.TimeFormatType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.trionesdev.template.core.domains.base.internal.BaseConstants.DEFAULT_CODE_FORMAT_RULES;

@RequiredArgsConstructor
@Service
public class CodeFormatRuleManager {
    private final ActorContext actorContext;
    private final CodeFormatRepository codeFormatRepository;


    public void createCodeFormatRule(CodeFormatRule record) {
        record.uniqueValidate(codeFormatRepository);
        codeFormatRepository.create(record);
    }

    public void deleteCodeFormatRuleById(String id) {
        codeFormatRepository.deleteById(id);
    }

    public void updateCodeFormatRuleById(CodeFormatRule record) {
        record.uniqueValidate(codeFormatRepository);
        codeFormatRepository.updateById(record);
    }

    public Optional<CodeFormatRule> findCodeFormatRuleById(String id) {
        return codeFormatRepository.findById(id);
    }

    public List<CodeFormatRule> findList() {
        var rules = codeFormatRepository.findList();
        return DEFAULT_CODE_FORMAT_RULES.stream().map(rule -> rule.merge(rules)).toList();
    }

    public Optional<CodeFormatRule> findByIdentifier(String identifier) {
        return codeFormatRepository.findByIdentifier(identifier).map(CodeFormatRule::mergeDefault).or(() -> {
            return DEFAULT_CODE_FORMAT_RULES.stream().filter(rule -> rule.getIdentifier().equals(identifier)).findFirst();
        });
    }

    @Lock(key = "#identifier")
    public String generateCode(String identifier) {
        CodeFormatRule customCodeRule = this.findByIdentifier(identifier).orElse(CodeFormatRule.builder().identifier(identifier).prefix(identifier.toLowerCase()).timeFormatType(TimeFormatType.YYYY).serialNumberDigits(4).build());
        Integer serialNumber = codeFormatRepository.nextSerialNumber(customCodeRule);
        return customCodeRule.generateCode(serialNumber);
    }

}
