package com.trionesdev.wms.core.domains.base.provider.impl;

import com.trionesdev.wms.core.domains.base.manager.impl.CodeFormatRuleManager;
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
