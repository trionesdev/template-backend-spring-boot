package com.trionesdev.mes.core.domains.perm.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.mes.core.domains.perm.dao.po.ResourceDraftPO;
import com.trionesdev.mes.core.domains.perm.dao.mapper.ResourceDraftMapper;
import com.trionesdev.mes.core.domains.perm.internal.enums.ClientType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResourceDraftDAO extends ServiceImpl<ResourceDraftMapper, ResourceDraftPO> {

    public List<ResourceDraftPO> selectListByClientType(ClientType clientType) {
        return lambdaQuery().eq(ResourceDraftPO::getClientType, clientType).list();
    }

}
