package com.trionesdev.wms.core.domains.format.service.impl;

import com.trionesdev.wms.core.domains.format.manager.impl.CodeFormatRuleManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CodeFormatRuleService {
    private final CodeFormatRuleManager customCodeRuleManager;

    public String generateCode(String identifier) {
        return customCodeRuleManager.generateCode(identifier);
    }


}
