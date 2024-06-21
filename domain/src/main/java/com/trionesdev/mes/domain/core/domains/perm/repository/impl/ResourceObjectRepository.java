package com.trionesdev.mes.domain.core.domains.perm.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.mes.domain.core.domains.perm.repository.mapper.ResourceObjectMapper;
import com.trionesdev.mes.domain.core.domains.perm.repository.po.ResourceObjectPO;
import org.springframework.stereotype.Repository;

@Repository
public class ResourceObjectRepository extends ServiceImpl<ResourceObjectMapper, ResourceObjectPO> {
}
