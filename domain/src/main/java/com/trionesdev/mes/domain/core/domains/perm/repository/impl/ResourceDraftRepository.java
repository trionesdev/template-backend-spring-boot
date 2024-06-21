package com.trionesdev.mes.domain.core.domains.perm.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.mes.domain.core.domains.perm.repository.mapper.ResourceDraftMapper;
import com.trionesdev.mes.domain.core.domains.perm.repository.po.ResourceDraftPO;
import org.springframework.stereotype.Repository;

@Repository
public class ResourceDraftRepository extends ServiceImpl<ResourceDraftMapper, ResourceDraftPO> {
}
