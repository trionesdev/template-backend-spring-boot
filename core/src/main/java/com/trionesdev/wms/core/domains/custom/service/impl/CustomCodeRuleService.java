package com.trionesdev.wms.core.domains.custom.service.impl;

import com.trionesdev.wms.core.domains.custom.manager.impl.CustomCodeRuleManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomCodeRuleService {
    private final CustomCodeRuleManager customCodeRuleManager;

    public String generateCode(String identifier) {
        return customCodeRuleManager.generateCode(identifier);
    }


}
