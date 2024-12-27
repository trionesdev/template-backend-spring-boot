package com.trionesdev.template.core.domains.base.repository.impl;

import com.trionesdev.template.core.domains.base.dao.impl.CodeFormatRuleDAO;
import com.trionesdev.template.core.domains.base.dao.impl.CodeFormatSerialNumberDAO;
import com.trionesdev.template.core.domains.base.dao.po.CodeFormatRulePO;
import com.trionesdev.template.core.domains.base.dao.po.CodeFormatSerialNumberPO;
import com.trionesdev.template.core.domains.base.internal.CodeFormatConvert;
import com.trionesdev.template.core.domains.base.internal.aggregate.entity.CodeFormatRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CodeFormatRepository {
    private final CodeFormatConvert convert;
    private final CodeFormatRuleDAO customCodeRuleDAO;
    private final CodeFormatSerialNumberDAO customCodeSerialNumberDAO;

    public void create(CodeFormatRule record) {
        var po = convert.codeFormatEntityToPo(record);
        customCodeRuleDAO.save(po);
    }

    public void deleteById(String id) {
        customCodeRuleDAO.removeById(id);
    }

    public void updateById(CodeFormatRule record) {
        var po = convert.codeFormatEntityToPo(record);
        customCodeRuleDAO.updateById(po);
    }

    public Optional<CodeFormatRule> findById(String id) {
        return Optional.ofNullable(customCodeRuleDAO.getById(id)).map(this::assemble);
    }

    private CodeFormatRule assemble(CodeFormatRulePO record) {
        return convert.codeFormatRulePoToEntity(record);
    }

    public List<CodeFormatRule> findList() {
        return customCodeRuleDAO.list().stream().map(convert::codeFormatRulePoToEntity).toList();
    }

    public Optional<CodeFormatRule> findByIdentifier(String identifier) {
        return Optional.ofNullable(customCodeRuleDAO.selectByIdentifier(identifier)).map(this::assemble);
    }

    public Integer nextSerialNumber(CodeFormatRule customCodeRule) {
        CodeFormatSerialNumberPO serialNumber = customCodeSerialNumberDAO.selectByUniqueIdentifier(customCodeRule.getIdentifier(), customCodeRule.timeIdentifier());
        if (Objects.isNull(serialNumber)) {
            CodeFormatSerialNumberPO po = CodeFormatSerialNumberPO.builder().identifier(customCodeRule.getIdentifier()).timeIdentifier(customCodeRule.timeIdentifier()).serialNumber(1).build();
            customCodeSerialNumberDAO.save(po);
            return 1;
        } else {
            serialNumber.setSerialNumber(serialNumber.getSerialNumber() + 1);
            customCodeSerialNumberDAO.updateByUniqueIdentifier(serialNumber);
            return serialNumber.getSerialNumber();
        }
    }

}
