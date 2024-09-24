package com.trionesdev.wms.core.domains.perm.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.wms.core.domains.perm.dao.po.FunctionalResourceDraftPO;
import com.trionesdev.wms.core.domains.perm.dao.mapper.FunctionalResourceDraftMapper;
import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FunctionalResourceDraftDAO extends ServiceImpl<FunctionalResourceDraftMapper, FunctionalResourceDraftPO> {

    public List<FunctionalResourceDraftPO> selectListByClientType(String appIdentifier, ClientType clientType) {
        return lambdaQuery().eq(StringUtils.isNoneBlank(appIdentifier), FunctionalResourceDraftPO::getAppIdentifier, appIdentifier).eq(FunctionalResourceDraftPO::getClientType, clientType).list();
    }

}
