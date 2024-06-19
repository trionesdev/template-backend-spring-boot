package com.trionesdev.mes.domain.core.provider.ssp.tenent.impl;


import com.trionesdev.mes.domain.core.domains.tenant.service.TenantService;
import com.trionesdev.mes.domain.core.dto.tenant.TenantMemberDTO;
import com.trionesdev.mes.domain.core.provider.ssp.tenent.TenantProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Component
public class TenantProviderLocal implements TenantProvider {
    private final TenantService tenantService;

    @Override
    public List<TenantMemberDTO> getMembersByMemberIds(Collection<String> memberIds) {
        return tenantService.findTenantMembersByMemberIds(memberIds);
    }
}
