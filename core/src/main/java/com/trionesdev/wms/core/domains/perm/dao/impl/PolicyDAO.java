package com.trionesdev.wms.core.domains.perm.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.wms.core.domains.perm.dao.mapper.PolicyMapper;
import com.trionesdev.wms.core.domains.perm.dao.po.PolicyPO;
import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.wms.core.domains.perm.internal.enums.PolicyGrantObjType;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Repository
public class PolicyDAO extends ServiceImpl<PolicyMapper, PolicyPO> {

    public void deleteByGrantObj(ClientType clientType, PolicyGrantObjType grantObjType, String grantObjId) {
        lambdaUpdate().eq(PolicyPO::getClientType, clientType).eq(PolicyPO::getGrantObjType, grantObjType)
                .eq(PolicyPO::getGrantObjId, grantObjId).remove();
    }

    public List<PolicyPO> selectByGrantObj(ClientType clientType, PolicyGrantObjType grantObjType, String grantObjId) {
        return lambdaQuery().eq(PolicyPO::getClientType, clientType).eq(PolicyPO::getGrantObjType, grantObjType)
                .eq(PolicyPO::getGrantObjId, grantObjId).list();
    }

    public List<PolicyPO> selectByGrantObjs(ClientType clientType, PolicyGrantObjType grantObjType, Collection<String> grantObjIds) {
        if (CollectionUtils.isEmpty(grantObjIds)) {
            return Collections.emptyList();
        }
        return lambdaQuery().eq(PolicyPO::getClientType, clientType).eq(PolicyPO::getGrantObjType, grantObjType)
                .in(PolicyPO::getGrantObjId, grantObjIds).list();
    }
}
