package com.trionesdev.wms.core.domains.perm.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.wms.core.domains.perm.dao.po.FunctionalResourceObjectPO;
import com.trionesdev.wms.core.domains.perm.dao.mapper.FunctionalResourceObjectMapper;
import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FunctionalResourceObjectDAO extends ServiceImpl<FunctionalResourceObjectMapper, FunctionalResourceObjectPO> {

    public void deleteByClientType(String appIdentifier, ClientType clientType) {
        lambdaUpdate().eq(StringUtils.isNoneBlank(appIdentifier), FunctionalResourceObjectPO::getAppIdentifier, appIdentifier).eq(FunctionalResourceObjectPO::getClientType, clientType).remove();
    }

    public List<FunctionalResourceObjectPO> selectListByClientType(String appIdentifier, ClientType clientType) {
        return lambdaQuery().eq(StringUtils.isNoneBlank(appIdentifier), FunctionalResourceObjectPO::getAppIdentifier, appIdentifier).eq(FunctionalResourceObjectPO::getClientType, clientType).list();
    }

}
