package com.trionesdev.mes.domain.core.provider.ssp.custom.impl;

import com.trionesdev.mes.domain.core.domains.custom.service.impl.CustomCodeRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CustomProvider {
    private final CustomCodeRuleService customCodeRuleService;

    public String generateCode(String identifier) {
        return customCodeRuleService.generateCode(identifier);
    }
}
