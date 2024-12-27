package com.trionesdev.template.core.domains.perm.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.template.core.domains.perm.dao.mapper.PermissionMapper;
import com.trionesdev.template.core.domains.perm.dao.po.PermissionPO;
import com.trionesdev.template.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.template.core.domains.perm.internal.enums.PermissionSubjectType;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Repository
public class PermissionDAO extends ServiceImpl<PermissionMapper, PermissionPO> {

    public void deleteBySubject(String appCode, ClientType clientType, PermissionSubjectType grantObjType, String grantObjId) {
        lambdaUpdate()
                .eq(StringUtils.isNotBlank(appCode), PermissionPO::getAppCode, appCode)
                .eq(Objects.nonNull(clientType), PermissionPO::getClientType, clientType)
                .eq(PermissionPO::getSubjectType, grantObjType)
                .eq(PermissionPO::getSubject, grantObjId).remove();
    }

    public List<PermissionPO> selectListBySubject(String appCode, ClientType clientType, PermissionSubjectType grantObjType, String grantObjId) {
        return lambdaQuery()
                .eq(StringUtils.isNotBlank(appCode), PermissionPO::getAppCode, appCode)
                .eq(Objects.nonNull(clientType), PermissionPO::getClientType, clientType)
                .eq(PermissionPO::getSubjectType, grantObjType)
                .eq(PermissionPO::getSubject, grantObjId).list();
    }

    public List<PermissionPO> selectListBySubjects(String appCode,ClientType clientType, PermissionSubjectType grantObjType, Collection<String> grantObjIds) {
        if (CollectionUtils.isEmpty(grantObjIds)) {
            return Collections.emptyList();
        }
        return lambdaQuery()
                .eq(StringUtils.isNotBlank(appCode), PermissionPO::getAppCode, appCode)
                .eq(Objects.nonNull(clientType), PermissionPO::getClientType, clientType)
                .eq(PermissionPO::getSubjectType, grantObjType)
                .in(PermissionPO::getSubject, grantObjIds).list();
    }
}
