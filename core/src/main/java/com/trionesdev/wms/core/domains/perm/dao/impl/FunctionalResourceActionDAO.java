package com.trionesdev.wms.core.domains.perm.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.wms.core.domains.perm.dao.po.FunctionalResourceActionPO;
import com.trionesdev.wms.core.domains.perm.dao.mapper.FunctionalResourceActionMapper;
import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FunctionalResourceActionDAO extends ServiceImpl<FunctionalResourceActionMapper, FunctionalResourceActionPO> {

    public void deleteByClientType(ClientType clientType) {
        lambdaUpdate().eq(FunctionalResourceActionPO::getClientType, clientType).remove();
    }

    public List<FunctionalResourceActionPO> selectListByClientType(ClientType clientType) {
        return lambdaQuery().eq(FunctionalResourceActionPO::getClientType, clientType).list();
    }

}
