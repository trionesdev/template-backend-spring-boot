package com.trionesdev.mes.domain.core.provider.ssp.tenent.impl;


import com.trionesdev.mes.domain.core.domains.tenant.service.TenantDomainService;
import com.trionesdev.mes.domain.core.dto.tenant.TenantDTO;
import com.trionesdev.mes.domain.core.dto.tenant.TenantMemberDTO;
import com.trionesdev.mes.domain.core.dto.tenant.TenantMemberSignInArg;
import com.trionesdev.mes.domain.core.provider.ssp.tenent.TenantProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Component
public class TenantProviderLocal implements TenantProvider {
    private final TenantDomainService tenantService;

    @Override
    public TenantDTO getTenantById(String id) {
        return tenantService.findTenantById(id).orElse(null);
    }

    @Override
    public TenantDTO getActorTenant() {
        return tenantService.findActorTenant().orElse(null);
    }

    @Override
    public TenantMemberDTO getMemberByMemberId(String memberId) {
        return tenantService.findTenantMemberByMemberId(memberId).orElse(null);
    }

    @Override
    public TenantMemberDTO getMemberByUserId(String userId) {
        return tenantService.findTenantMemberByUserId(userId).orElse(null);
    }

    @Override
    public List<TenantMemberDTO> getMembersByMemberIds(Collection<String> memberIds) {
        return tenantService.findTenantMembersByMemberIds(memberIds);
    }

    @Override
    public TenantMemberDTO tenantMemberSignIn(TenantMemberSignInArg arg) {
        return tenantService.tenantMemberSignIn(arg);
    }
}
