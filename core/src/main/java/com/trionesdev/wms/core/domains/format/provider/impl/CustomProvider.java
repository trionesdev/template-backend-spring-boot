package com.trionesdev.wms.core.domains.format.provider.impl;

import com.trionesdev.wms.core.domains.format.manager.impl.CodeFormatRuleManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CustomProvider {
    private final CodeFormatRuleManager customCodeRuleManager;

    public String generateCode(String identifier) {
        return customCodeRuleManager.generateCode(identifier);
    }
}
