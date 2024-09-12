package com.trionesdev.wms.core.domains.custom.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.wms.core.domains.custom.dao.mapper.CustomCodeRuleMapper;
import com.trionesdev.wms.core.domains.custom.dao.po.CustomCodeRulePO;
import org.springframework.stereotype.Repository;

@Repository
public class CustomCodeRuleDAO extends ServiceImpl<CustomCodeRuleMapper, CustomCodeRulePO> {

    public CustomCodeRulePO selectByIdentifier(String identifier) {
        return lambdaQuery()
                .eq(CustomCodeRulePO::getIdentifier, identifier)
                .one();
    }

}
