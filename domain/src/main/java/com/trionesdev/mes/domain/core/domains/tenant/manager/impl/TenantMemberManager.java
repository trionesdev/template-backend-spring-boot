package com.trionesdev.mes.domain.core.domains.tenant.manager.impl;

import com.trionesdev.mes.domain.core.domains.tenant.repository.impl.TenantMemberRepository;
import com.trionesdev.mes.domain.core.domains.tenant.repository.po.TenantMemberPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TenantMemberManager {
    private final TenantMemberRepository tenantMemberRepository;

    public void createMember(TenantMemberPO tenantMember) {
        tenantMemberRepository.save(tenantMember);
    }

    public void deleteMemberById(String id) {
        tenantMemberRepository.removeById(id);
    }

    public void updateMemberById(TenantMemberPO tenantMember) {
        tenantMemberRepository.save(tenantMember);
    }

    public Optional<TenantMemberPO> findMemberById(String id) {
        return Optional.ofNullable(tenantMemberRepository.getById(id));
    }

    public Optional<TenantMemberPO> findMemberByUserId(String userId) {
        return Optional.ofNullable(tenantMemberRepository.selectByUserId(userId));
    }


}
