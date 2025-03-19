package com.trionesdev.template.core.domains.perm.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.template.core.domains.perm.dao.mapper.FunctionalResourceDraftMapper;
import com.trionesdev.template.core.domains.perm.dao.po.FunctionalResourceDraftPO;
import com.trionesdev.template.core.domains.perm.shared.enums.ClientType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class FunctionalResourceDraftDAO extends ServiceImpl<FunctionalResourceDraftMapper, FunctionalResourceDraftPO> {
    public List<FunctionalResourceDraftPO> selectListByAppClient(String appCode, ClientType clientType) {
        return lambdaQuery().eq(StringUtils.isNotBlank(appCode), FunctionalResourceDraftPO::getAppCode, appCode)
                .eq(Objects.nonNull(clientType), FunctionalResourceDraftPO::getClientType, clientType).list();
    }

    public void deleteByAppClient(String appCode, ClientType clientType) {
        lambdaUpdate().eq(StringUtils.isNoneBlank(appCode), FunctionalResourceDraftPO::getAppCode, appCode)
                .eq(Objects.nonNull(clientType), FunctionalResourceDraftPO::getClientType, clientType).remove();
    }
}
