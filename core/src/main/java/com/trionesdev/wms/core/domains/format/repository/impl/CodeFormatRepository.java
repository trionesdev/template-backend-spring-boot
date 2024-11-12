package com.trionesdev.wms.core.domains.format.repository.impl;

import com.trionesdev.wms.core.domains.format.dao.impl.CodeFormatRuleDAO;
import com.trionesdev.wms.core.domains.format.dao.po.CodeFormatRulePO;
import com.trionesdev.wms.core.domains.format.internal.CodeFormatConvert;
import com.trionesdev.wms.core.domains.format.internal.aggregate.entity.CodeFormatRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CodeFormatRepository {
    private final CodeFormatConvert convert;
    private final CodeFormatRuleDAO customCodeRuleDAO;

    public void create(CodeFormatRule record) {
        var po = convert.codeFormatEntityToPo(record);
        customCodeRuleDAO.save(po);
    }

    private CodeFormatRule assemble(CodeFormatRulePO record){
        return convert.codeFormatRulePoToEntity(record);
    }

    public List<CodeFormatRule> findList() {
        return customCodeRuleDAO.list().stream().map(convert::codeFormatRulePoToEntity).toList();
    }

    public Optional<CodeFormatRule> findByIdentifier(String identifier) {
        return Optional.ofNullable(customCodeRuleDAO.selectByIdentifier(identifier)).map(this::assemble);
    }

}
