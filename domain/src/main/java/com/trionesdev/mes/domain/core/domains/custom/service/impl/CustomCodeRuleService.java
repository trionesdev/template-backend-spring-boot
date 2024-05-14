package com.trionesdev.mes.domain.core.domains.custom.service.impl;

import com.trionesdev.commons.exception.NotFoundException;
import com.trionesdev.mes.domain.core.domains.custom.entity.CustomCodeRule;
import com.trionesdev.mes.domain.core.domains.custom.manager.impl.CustomCodeRuleManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class CustomCodeRuleService {
    private final CustomCodeRuleManager customCodeRuleManager;

    public String generateCode(String identifier) {
        CustomCodeRule customCodeRule = customCodeRuleManager.findByIdentifier(identifier);
        if (Objects.isNull(customCodeRule)) {
            throw new NotFoundException("CUSTOM_CODE_RULE_NOT_FOUND");
        }
        Integer serialNumber = customCodeRuleManager.nextSerialNumber(identifier, customCodeRule.timeIdentifier());
        return customCodeRule.generateCode(serialNumber);
    }


}
