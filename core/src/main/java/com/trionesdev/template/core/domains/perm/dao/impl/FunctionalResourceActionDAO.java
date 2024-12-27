package com.trionesdev.template.core.domains.perm.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.template.core.domains.perm.dao.po.FunctionalResourceActionPO;
import com.trionesdev.template.core.domains.perm.dao.mapper.FunctionalResourceActionMapper;
import com.trionesdev.template.core.domains.perm.internal.enums.ClientType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FunctionalResourceActionDAO extends ServiceImpl<FunctionalResourceActionMapper, FunctionalResourceActionPO> {

    public void deleteByClientType(String appIdentifier, ClientType clientType) {
        lambdaUpdate().eq(StringUtils.isNoneBlank(appIdentifier),FunctionalResourceActionPO::getAppIdentifier, appIdentifier).eq(FunctionalResourceActionPO::getClientType, clientType).remove();
    }

    public List<FunctionalResourceActionPO> selectListByClientType(String appIdentifier, ClientType clientType) {
        return lambdaQuery().eq(StringUtils.isNoneBlank(appIdentifier),FunctionalResourceActionPO::getAppIdentifier, appIdentifier).eq(FunctionalResourceActionPO::getClientType, clientType).list();
    }

}
