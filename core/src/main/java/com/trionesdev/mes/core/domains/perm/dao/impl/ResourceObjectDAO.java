package com.trionesdev.mes.core.domains.perm.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.mes.core.domains.perm.dao.po.ResourceObjectPO;
import com.trionesdev.mes.core.domains.perm.dao.mapper.ResourceObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ResourceObjectDAO extends ServiceImpl<ResourceObjectMapper, ResourceObjectPO> {
}
