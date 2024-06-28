package com.trionesdev.mes.core.domains.perm.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.mes.core.domains.perm.dao.po.ResourceActionPO;
import com.trionesdev.mes.core.domains.perm.dao.mapper.ResourceActionMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ResourceActionDAO extends ServiceImpl<ResourceActionMapper, ResourceActionPO> {
}
