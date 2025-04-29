package com.trionesdev.template.core.domains.boss.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.template.core.domains.boss.dao.mapper.BossFunctionalResourceDraftMapper;
import com.trionesdev.template.core.domains.boss.dao.po.BossFunctionalResourceDraftPO;
import com.trionesdev.template.core.domains.boss.shared.enums.ClientType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class BossFunctionalResourceDraftDAO extends ServiceImpl<BossFunctionalResourceDraftMapper, BossFunctionalResourceDraftPO> {
    public List<BossFunctionalResourceDraftPO> selectListByAppClient(ClientType clientType) {
        return lambdaQuery()
                .eq(Objects.nonNull(clientType), BossFunctionalResourceDraftPO::getClientType, clientType).list();
    }

    public void deleteByAppClient(ClientType clientType) {
        lambdaUpdate()
                .eq(Objects.nonNull(clientType), BossFunctionalResourceDraftPO::getClientType, clientType).remove();
    }
}
