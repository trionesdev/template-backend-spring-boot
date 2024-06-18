package com.trionesdev.mes.domain.core.domains.tenant.service.impl;

import com.trionesdev.mes.domain.core.domains.tenant.entity.TenantMember;
import com.trionesdev.mes.domain.core.domains.tenant.internal.TenantBeanConvert;
import com.trionesdev.mes.domain.core.domains.tenant.manager.impl.TenantMemberManager;
import com.trionesdev.mes.domain.core.domains.tenant.service.TenantService;
import com.trionesdev.mes.domain.core.dto.user.UserCreateDTO;
import com.trionesdev.mes.domain.core.provider.ssp.user.UserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TenantServiceLocal implements TenantService {
    private final TenantBeanConvert convert;
    private final TenantMemberManager tenantMemberManager;
    private final UserProvider userProvider;

    @Transactional
    @Override
    public void createTenantMember(TenantMember tenantMember) {
        var userDTO = UserCreateDTO.builder().phone(tenantMember.getPhone()).build();
        var userId = userProvider.createUser(userDTO);
        var tenantMemberPO = convert.entityToPO(tenantMember);
        tenantMemberPO.setUserId(userId);
        tenantMemberManager.createMember(tenantMemberPO);
    }
}
