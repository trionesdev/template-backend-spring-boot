package com.trionesdev.template.core.domains.base.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.template.core.domains.base.dao.mapper.CodeFormatRuleMapper;
import com.trionesdev.template.core.domains.base.dao.po.CodeFormatRulePO;
import org.springframework.stereotype.Repository;

@Repository
public class CodeFormatRuleDAO extends ServiceImpl<CodeFormatRuleMapper, CodeFormatRulePO> {

    public CodeFormatRulePO selectByIdentifier(String identifier) {
        return lambdaQuery()
                .eq(CodeFormatRulePO::getIdentifier, identifier)
                .one();
    }

}
