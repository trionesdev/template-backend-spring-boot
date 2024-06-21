package com.trionesdev.mes.domain.core.domains.perm.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.mes.domain.core.domains.perm.repository.mapper.PolicyMapper;
import com.trionesdev.mes.domain.core.domains.perm.repository.po.PolicyPO;
import org.springframework.stereotype.Repository;

@Repository
public class PolicyRepository extends ServiceImpl<PolicyMapper, PolicyPO> {
}
