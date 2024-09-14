package com.trionesdev.wms.core.domains.perm.manager.impl;

import com.trionesdev.wms.core.domains.perm.internal.aggregate.entity.Permission;
import com.trionesdev.wms.core.domains.perm.internal.aggregate.entity.Policy;
import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.wms.core.domains.perm.internal.enums.PolicyGrantObjType;
import com.trionesdev.wms.core.domains.perm.repository.impl.PolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class PolicyManager {
    private final PolicyRepository policyRepository;

    public void savePolicy(Policy policy) {
        policyRepository.savePolicy(policy);
    }

    public Set<Permission> findPermissionsByGrantObj(ClientType clientType, PolicyGrantObjType grantObjType, String grantObjId) {
        return policyRepository.findPermissionsByGrantObj(clientType, grantObjType, grantObjId);
    }

    public Set<Permission> findPermissionsByGrantObjs(ClientType clientType, PolicyGrantObjType grantObjType, Collection<String> grantObjIds) {
        return policyRepository.findPermissionsByGrantObjs(clientType, grantObjType, grantObjIds);
    }

}
