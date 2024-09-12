package com.trionesdev.wms.core.domains.perm.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.wms.core.domains.perm.dao.mapper.PolicyMapper;
import com.trionesdev.wms.core.domains.perm.dao.po.PolicyPO;
import org.springframework.stereotype.Repository;

@Repository
public class PolicyDAO extends ServiceImpl<PolicyMapper, PolicyPO> {
}
