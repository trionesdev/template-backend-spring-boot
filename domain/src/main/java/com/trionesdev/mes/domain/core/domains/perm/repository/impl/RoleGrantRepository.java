package com.trionesdev.mes.domain.core.domains.perm.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.mes.domain.core.domains.perm.repository.mapper.RoleGrantMapper;
import com.trionesdev.mes.domain.core.domains.perm.repository.po.RoleGrantPO;
import org.springframework.stereotype.Repository;

@Repository
public class RoleGrantRepository extends ServiceImpl<RoleGrantMapper, RoleGrantPO> {
}
