package com.trionesdev.wms.core.domains.perm.manager.impl;

import com.trionesdev.wms.core.domains.perm.internal.aggregate.entity.Policy;
import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.wms.core.domains.perm.internal.enums.PolicyGrantObjType;
import com.trionesdev.wms.core.domains.perm.repository.impl.PolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class PolicyManager {
    private final PolicyRepository policyRepository;

    public void savePolicy(Policy policy) {
        policyRepository.savePolicy(policy);
    }



}
