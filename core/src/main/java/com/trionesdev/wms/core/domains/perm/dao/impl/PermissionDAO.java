package com.trionesdev.wms.core.domains.perm.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.wms.core.domains.perm.dao.mapper.PermissionMapper;
import com.trionesdev.wms.core.domains.perm.dao.po.PermissionPO;
import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.wms.core.domains.perm.internal.enums.PermissionSubjectType;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Repository
public class PermissionDAO extends ServiceImpl<PermissionMapper, PermissionPO> {

    public void deleteBySubject(ClientType clientType, PermissionSubjectType grantObjType, String grantObjId) {
        lambdaUpdate().eq(Objects.nonNull(clientType),PermissionPO::getClientType, clientType).eq(PermissionPO::getSubjectType, grantObjType)
                .eq(PermissionPO::getSubject, grantObjId).remove();
    }

    public List<PermissionPO> selectListBySubject(ClientType clientType, PermissionSubjectType grantObjType, String grantObjId) {
        return lambdaQuery().eq(PermissionPO::getClientType, clientType).eq(PermissionPO::getSubjectType, grantObjType)
                .eq(PermissionPO::getSubject, grantObjId).list();
    }

    public List<PermissionPO> selectListBySubjects(ClientType clientType, PermissionSubjectType grantObjType, Collection<String> grantObjIds) {
        if (CollectionUtils.isEmpty(grantObjIds)) {
            return Collections.emptyList();
        }
        return lambdaQuery().eq(PermissionPO::getClientType, clientType).eq(PermissionPO::getSubjectType, grantObjType)
                .in(PermissionPO::getSubject, grantObjIds).list();
    }
}
