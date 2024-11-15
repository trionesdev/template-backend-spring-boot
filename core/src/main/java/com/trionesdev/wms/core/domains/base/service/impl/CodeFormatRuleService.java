package com.trionesdev.wms.core.domains.base.service.impl;

import com.trionesdev.wms.core.domains.base.dto.CodeFormatRuleDTO;
import com.trionesdev.wms.core.domains.base.internal.CodeFormatConvert;
import com.trionesdev.wms.core.domains.base.internal.aggregate.entity.CodeFormatRule;
import com.trionesdev.wms.core.domains.base.manager.impl.CodeFormatRuleManager;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CodeFormatRuleService {
    private final CodeFormatConvert codeFormatConvert;
    private final CodeFormatRuleManager customCodeRuleManager;

    public void createCodeFormatRule(CodeFormatRule record) {
        customCodeRuleManager.createCodeFormatRule(record);
    }

    public void deleteCodeFormatRuleById(String id) {
        customCodeRuleManager.deleteCodeFormatRuleById(id);
    }

    public void updateCodeFormatRuleById(CodeFormatRule record) {
        customCodeRuleManager.updateCodeFormatRuleById(record);
    }

    private CodeFormatRuleDTO assembleCodeFormatRule(CodeFormatRule record) {
        return codeFormatConvert.codeFormatEntityToDto(record);
    }

    public Optional<CodeFormatRuleDTO> findCodeFormatRuleById(String id) {
        return customCodeRuleManager.findCodeFormatRuleById(id).map(this::assembleCodeFormatRule);
    }

    private List<CodeFormatRuleDTO> assembleCodeFormatRules(List<CodeFormatRule> records) {
        if (CollectionUtils.isEmpty(records)) {
            return Collections.emptyList();
        }
        return records.stream().map(record -> {
            return codeFormatConvert.codeFormatEntityToDto(record);
        }).collect(Collectors.toList());
    }

    public List<CodeFormatRuleDTO> findList() {
        return assembleCodeFormatRules(customCodeRuleManager.findList());
    }


}
