package com.trionesdev.wms.core.domains.perm.repository.impl;

import com.trionesdev.wms.core.domains.perm.dao.impl.PolicyDAO;
import com.trionesdev.wms.core.domains.perm.dao.po.PolicyPO;
import com.trionesdev.wms.core.domains.perm.internal.aggregate.entity.Permission;
import com.trionesdev.wms.core.domains.perm.internal.aggregate.entity.Policy;
import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.wms.core.domains.perm.internal.enums.PolicyGrantObjType;
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
    private final PolicyDAO policyDAO;

    @Transactional
    public void savePolicy(Policy policy) {
        policyDAO.deleteByGrantObj(policy.getClientType(), policy.getGrantObjType(), policy.getGrantObjId());
        if (CollectionUtils.isNotEmpty(policy.getPermissions())) {
            List<PolicyPO> policies = policy.getPermissions().stream().map(permission -> PolicyPO.builder()
                    .clientType(policy.getClientType())
                    .build()).collect(Collectors.toList());
            policyDAO.saveBatch(policies);
        }
    }

    public void deleteByGrantObj(ClientType clientType, PolicyGrantObjType grantObjType, String grantObjId) {
        policyDAO.deleteByGrantObj(clientType, grantObjType, grantObjId);
    }


    public Set<Permission> findPermissionsByGrantObj(ClientType clientType, PolicyGrantObjType grantObjType, String grantObjId) {
        var policies = policyDAO.selectByGrantObj(clientType, grantObjType, grantObjId);
        return policies.stream().map(policy -> Permission.builder().obj(policy.getObj()).act(policy.getAct()).build()).collect(Collectors.toSet());
    }

    public List<Permission> findPermissionsByGrantObjs(ClientType clientType, PolicyGrantObjType grantObjType, Collection<String> grantObjIds) {
        var policies = policyDAO.selectByGrantObjs(clientType, grantObjType, grantObjIds);
        return policies.stream().map(policy -> Permission.builder().obj(policy.getObj()).act(policy.getAct()).build()).collect(Collectors.toList());
    }

}
