package com.trionesdev.wms.core.domains.perm.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.wms.core.domains.perm.dao.po.FunctionalResourceObjectPO;
import com.trionesdev.wms.core.domains.perm.dao.mapper.FunctionalResourceObjectMapper;
import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FunctionalResourceObjectDAO extends ServiceImpl<FunctionalResourceObjectMapper, FunctionalResourceObjectPO> {

    public void deleteByClientType(ClientType clientType) {
        lambdaUpdate().eq(FunctionalResourceObjectPO::getClientType, clientType).remove();
    }

    public List<FunctionalResourceObjectPO> selectListByClientType(ClientType clientType) {
        return lambdaQuery().eq(FunctionalResourceObjectPO::getClientType, clientType).list();
    }

}
