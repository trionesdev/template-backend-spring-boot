package com.trionesdev.wms.core.domains.perm.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.wms.core.domains.perm.dao.po.ResourceActionPO;
import com.trionesdev.wms.core.domains.perm.dao.mapper.ResourceActionMapper;
import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResourceActionDAO extends ServiceImpl<ResourceActionMapper, ResourceActionPO> {

    public void deleteByClientType(ClientType clientType) {
        lambdaUpdate().eq(ResourceActionPO::getClientType, clientType).remove();
    }

    public List<ResourceActionPO> selectListByClientType(ClientType clientType) {
        return lambdaQuery().eq(ResourceActionPO::getClientType, clientType).list();
    }

}
