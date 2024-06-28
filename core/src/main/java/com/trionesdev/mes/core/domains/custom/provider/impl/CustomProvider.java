package com.trionesdev.mes.core.domains.custom.provider.impl;

import com.trionesdev.mes.core.domains.custom.manager.impl.CustomCodeRuleManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CustomProvider {
    private final CustomCodeRuleManager customCodeRuleManager;

    public String generateCode(String identifier) {
        return customCodeRuleManager.generateCode(identifier);
    }
}
