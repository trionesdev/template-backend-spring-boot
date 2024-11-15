package com.trionesdev.wms.core.domains.base.manager.impl;

import com.trionesdev.commons.context.actor.ActorContext;
import com.trionesdev.spring.lock.Lock;
import com.trionesdev.wms.core.domains.base.internal.aggregate.entity.CodeFormatRule;
import com.trionesdev.wms.core.domains.base.internal.enums.TimeFormatType;
import com.trionesdev.wms.core.domains.base.repository.impl.CodeFormatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return codeFormatRepository.findList();
    }

    public Optional<CodeFormatRule> findByIdentifier(String identifier) {
        return codeFormatRepository.findByIdentifier(identifier);
    }

    @Lock(key = "#{identifier}")
    public String generateCode(String identifier) {
        CodeFormatRule customCodeRule = this.findByIdentifier(identifier).orElse(CodeFormatRule.builder().identifier(identifier).prefix(identifier.toLowerCase()).timeFormatType(TimeFormatType.YYYY).serialNumberDigits(4).build());
        Integer serialNumber = codeFormatRepository.nextSerialNumber(customCodeRule);
        return customCodeRule.generateCode(serialNumber);
    }

}
