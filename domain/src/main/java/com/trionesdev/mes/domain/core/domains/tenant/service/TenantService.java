package com.trionesdev.mes.domain.core.domains.tenant.service;

import com.trionesdev.mes.domain.core.domains.tenant.entity.TenantMember;
import com.trionesdev.mes.domain.core.domains.tenant.repository.po.TenantPO;
import com.trionesdev.mes.domain.core.domains.tenant.service.bo.TenantMemberSignInArg;
import com.trionesdev.mes.domain.core.dto.tenant.TenantMemberDTO;

import java.util.Collection;
import java.util.List;

public interface TenantService {

    void createTenant(TenantPO tenantPO);

    void updateTenantById(TenantPO tenantPO);

    void createTenantMember(TenantMember tenantMember);

    List<TenantMemberDTO> findTenantMembersByMemberIds(Collection<String> memberIds);

    String tenantMemberSignIn(TenantMemberSignInArg arg);
}
