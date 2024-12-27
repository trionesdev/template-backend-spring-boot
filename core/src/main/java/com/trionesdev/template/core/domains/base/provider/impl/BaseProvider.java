package com.trionesdev.template.core.domains.base.provider.impl;

import com.trionesdev.template.core.domains.base.manager.impl.CodeFormatRuleManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BaseProvider {
    private final CodeFormatRuleManager customCodeRuleManager;

    public String generateCode(String identifier) {
        return customCodeRuleManager.generateCode(identifier);
    }
}
