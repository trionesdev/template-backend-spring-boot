package com.trionesdev.mes.domain.core.domains.perm.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.mes.domain.core.domains.perm.repository.mapper.ResourceActionMapper;
import com.trionesdev.mes.domain.core.domains.perm.repository.po.ResourceActionPO;
import org.springframework.stereotype.Repository;

@Repository
public class ResourceActionRepository extends ServiceImpl<ResourceActionMapper, ResourceActionPO> {
}
