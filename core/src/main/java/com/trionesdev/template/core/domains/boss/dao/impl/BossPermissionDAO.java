package com.trionesdev.template.core.domains.boss.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.template.core.domains.boss.dao.mapper.BossPermissionMapper;
import com.trionesdev.template.core.domains.boss.dao.po.BossPermissionPO;
import com.trionesdev.template.core.domains.boss.shared.enums.ClientType;
import com.trionesdev.template.core.domains.boss.shared.enums.PermissionSubjectType;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Repository
public class BossPermissionDAO extends ServiceImpl<BossPermissionMapper, BossPermissionPO> {

    public void deleteBySubject(ClientType clientType, PermissionSubjectType grantObjType, String subject) {
        lambdaUpdate()

                .eq(Objects.nonNull(clientType), BossPermissionPO::getClientType, clientType)
                .eq(BossPermissionPO::getSubjectType, grantObjType)
                .eq(BossPermissionPO::getSubject, subject).remove();
    }

    public List<BossPermissionPO> selectListBySubject(ClientType clientType, PermissionSubjectType grantObjType, String subject) {
        return lambdaQuery()

                .eq(Objects.nonNull(clientType), BossPermissionPO::getClientType, clientType)
                .eq(BossPermissionPO::getSubjectType, grantObjType)
                .eq(BossPermissionPO::getSubject, subject).list();
    }

    public List<BossPermissionPO> selectListBySubjects(ClientType clientType, PermissionSubjectType subjectType, Collection<String> subjects) {
        if (CollectionUtils.isEmpty(subjects)) {
            return Collections.emptyList();
        }
        return lambdaQuery()

                .eq(Objects.nonNull(clientType), BossPermissionPO::getClientType, clientType)
                .eq(BossPermissionPO::getSubjectType, subjectType)
                .in(BossPermissionPO::getSubject, subjects).list();
    }

    public List<BossPermissionPO> selectResourcesBySubjects(ClientType clientType, PermissionSubjectType subjectType, Collection<String> subjects) {
        if (CollectionUtils.isEmpty(subjects)) {
            return Collections.emptyList();
        }
        return lambdaQuery().select(BossPermissionPO::getResourceCode)

                .eq(Objects.nonNull(clientType), BossPermissionPO::getClientType, clientType)
                .eq(BossPermissionPO::getSubjectType, subjectType)
                .in(BossPermissionPO::getSubject, subjects)
                .groupBy(BossPermissionPO::getResourceCode)
                .list();
    }
}
