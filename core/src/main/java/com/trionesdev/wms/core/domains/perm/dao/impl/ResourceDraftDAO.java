package com.trionesdev.wms.core.domains.perm.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.wms.core.domains.perm.dao.po.ViewResourceDraftPO;
import com.trionesdev.wms.core.domains.perm.dao.mapper.ResourceDraftMapper;
import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResourceDraftDAO extends ServiceImpl<ResourceDraftMapper, ViewResourceDraftPO> {

    public List<ViewResourceDraftPO> selectListByClientType(ClientType clientType) {
        return lambdaQuery().eq(ViewResourceDraftPO::getClientType, clientType).list();
    }

}
