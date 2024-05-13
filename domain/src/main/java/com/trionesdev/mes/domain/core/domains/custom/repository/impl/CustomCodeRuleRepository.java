package com.trionesdev.mes.domain.core.domains.custom.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.mes.domain.core.domains.custom.repository.mapper.CustomCodeRuleMapper;
import com.trionesdev.mes.domain.core.domains.custom.repository.po.CustomCodeRulePO;
import org.springframework.stereotype.Repository;

@Repository
public class CustomCodeRuleRepository extends ServiceImpl<CustomCodeRuleMapper, CustomCodeRulePO> {
}
