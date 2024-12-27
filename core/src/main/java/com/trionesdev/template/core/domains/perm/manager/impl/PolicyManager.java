package com.trionesdev.template.core.domains.perm.manager.impl;

import com.trionesdev.template.core.domains.perm.internal.aggregate.entity.Permission;
import com.trionesdev.template.core.domains.perm.internal.aggregate.entity.Policy;
import com.trionesdev.template.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.template.core.domains.perm.internal.enums.PermissionSubjectType;
import com.trionesdev.template.core.domains.perm.repository.impl.PolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class PolicyManager {
    private final PolicyRepository policyRepository;

    public void savePolicy(Policy policy) {
        policyRepository.savePolicy(policy);
    }

    public Set<Permission> findPermissionsBySubject(String appCode,ClientType clientType, PermissionSubjectType subjectType, String subject) {
        return policyRepository.findPermissionsBySubject(appCode,clientType, subjectType, subject);
    }

    public Set<Permission> findPermissionsBySubjects(String appCode,ClientType clientType, PermissionSubjectType subjectType, Collection<String> subjects) {
        return policyRepository.findPermissionsBySubjects(appCode,clientType, subjectType, subjects);
    }

}
