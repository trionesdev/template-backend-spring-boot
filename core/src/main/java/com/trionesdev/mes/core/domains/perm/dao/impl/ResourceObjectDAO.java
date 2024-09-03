package com.trionesdev.mes.core.domains.perm.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.mes.core.domains.perm.dao.po.ResourceObjectPO;
import com.trionesdev.mes.core.domains.perm.dao.mapper.ResourceObjectMapper;
import com.trionesdev.mes.core.domains.perm.internal.enums.ClientType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResourceObjectDAO extends ServiceImpl<ResourceObjectMapper, ResourceObjectPO> {

    public void deleteByClientType(ClientType clientType) {
        lambdaUpdate().eq(ResourceObjectPO::getClientType, clientType).remove();
    }

    public List<ResourceObjectPO> selectListByClientType(ClientType clientType) {
        return lambdaQuery().eq(ResourceObjectPO::getClientType, clientType).list();
    }

}
