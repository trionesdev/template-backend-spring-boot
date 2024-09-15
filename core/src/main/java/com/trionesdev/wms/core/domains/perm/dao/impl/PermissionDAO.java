package com.trionesdev.wms.core.domains.perm.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.wms.core.domains.perm.dao.mapper.PermissionMapper;
import com.trionesdev.wms.core.domains.perm.dao.po.PermissionPO;
import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.wms.core.domains.perm.internal.enums.PolicyGrantObjType;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Repository
public class PermissionDAO extends ServiceImpl<PermissionMapper, PermissionPO> {

    public void deleteByGrantObj(ClientType clientType, PolicyGrantObjType grantObjType, String grantObjId) {
        lambdaUpdate().eq(PermissionPO::getClientType, clientType).eq(PermissionPO::getGrantObjType, grantObjType)
                .eq(PermissionPO::getGrantObjId, grantObjId).remove();
    }

    public List<PermissionPO> selectByGrantObj(ClientType clientType, PolicyGrantObjType grantObjType, String grantObjId) {
        return lambdaQuery().eq(PermissionPO::getClientType, clientType).eq(PermissionPO::getGrantObjType, grantObjType)
                .eq(PermissionPO::getGrantObjId, grantObjId).list();
    }

    public List<PermissionPO> selectByGrantObjs(ClientType clientType, PolicyGrantObjType grantObjType, Collection<String> grantObjIds) {
        if (CollectionUtils.isEmpty(grantObjIds)) {
            return Collections.emptyList();
        }
        return lambdaQuery().eq(PermissionPO::getClientType, clientType).eq(PermissionPO::getGrantObjType, grantObjType)
                .in(PermissionPO::getGrantObjId, grantObjIds).list();
    }
}
