package com.trionesdev.wms.core.domains.perm.repository.impl;

import com.trionesdev.wms.core.domains.perm.dao.impl.PermissionDAO;
import com.trionesdev.wms.core.domains.perm.dao.po.PermissionPO;
import com.trionesdev.wms.core.domains.perm.internal.aggregate.entity.Permission;
import com.trionesdev.wms.core.domains.perm.internal.aggregate.entity.Policy;
import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.wms.core.domains.perm.internal.enums.PermissionSubjectType;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class PolicyRepository {
    private final PermissionDAO policyDAO;

    @Transactional
    public void savePolicy(Policy policy) {
        policyDAO.deleteBySubject(policy.getClientType(), policy.getSubjectType(), policy.getSubject());
        if (CollectionUtils.isNotEmpty(policy.getPermissions())) {
            List<PermissionPO> policies = policy.getPermissions().stream().map(permission -> PermissionPO.builder()
                    .appCode(policy.getAppCode())
                    .clientType(policy.getClientType())
                    .subjectType(policy.getSubjectType())
                    .subject(policy.getSubject())
                    .obj(permission.getObj())
                    .effect(permission.getEffect())
                    .build()).collect(Collectors.toList());
            policyDAO.saveBatch(policies);
        }
    }

    public void deleteBySubject(ClientType clientType, PermissionSubjectType subjectType, String subject) {
        policyDAO.deleteBySubject(clientType, subjectType, subject);
    }


    public Set<Permission> findPermissionsBySubject(ClientType clientType, PermissionSubjectType grantObjType, String grantObjId) {
        var policies = policyDAO.selectListBySubject(clientType, grantObjType, grantObjId);
        return policies.stream().map(policy -> Permission.builder().obj(policy.getObj()).effect(policy.getEffect()).build()).collect(Collectors.toSet());
    }

    public Set<Permission> findPermissionsBySubjects(ClientType clientType, PermissionSubjectType grantObjType, Collection<String> grantObjIds) {
        var policies = policyDAO.selectListBySubjects(clientType, grantObjType, grantObjIds);
        return policies.stream().map(policy -> Permission.builder().obj(policy.getObj()).effect(policy.getEffect()).build()).collect(Collectors.toSet());
    }

}
