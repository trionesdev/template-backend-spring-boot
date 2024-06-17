package com.trionesdev.mes.domain.core.domains.tenant.service.impl;

import com.trionesdev.mes.domain.core.domains.tenant.entity.TenantMember;
import com.trionesdev.mes.domain.core.domains.tenant.manager.impl.TenantMemberManager;
import com.trionesdev.mes.domain.core.domains.tenant.service.TenantService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TenantServiceLocal implements TenantService {
    private final TenantMemberManager tenantMemberManager;

    @Override
    public void createTenantMember(TenantMember tenantMember) {

    }
}
