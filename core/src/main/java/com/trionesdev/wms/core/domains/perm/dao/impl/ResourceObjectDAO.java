package com.trionesdev.wms.core.domains.perm.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.wms.core.domains.perm.dao.po.ViewResourceObjectPO;
import com.trionesdev.wms.core.domains.perm.dao.mapper.ResourceObjectMapper;
import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResourceObjectDAO extends ServiceImpl<ResourceObjectMapper, ViewResourceObjectPO> {

    public void deleteByClientType(ClientType clientType) {
        lambdaUpdate().eq(ViewResourceObjectPO::getClientType, clientType).remove();
    }

    public List<ViewResourceObjectPO> selectListByClientType(ClientType clientType) {
        return lambdaQuery().eq(ViewResourceObjectPO::getClientType, clientType).list();
    }

}
