package com.trionesdev.mes.domain.core.domains.tenant.service;

import com.trionesdev.mes.domain.core.domains.tenant.entity.TenantMember;
import com.trionesdev.mes.domain.core.domains.tenant.repository.po.TenantPO;
import com.trionesdev.mes.domain.core.dto.tenant.TenantMemberSignInArg;
import com.trionesdev.mes.domain.core.dto.tenant.TenantMemberDTO;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface TenantService {

    void createTenant(TenantPO tenantPO);

    void updateTenantById(TenantPO tenantPO);

    void createTenantMember(TenantMember tenantMember);

    List<TenantMemberDTO> findTenantMembersByMemberIds(Collection<String> memberIds);

    Optional<TenantMemberDTO> findTenantMemberByMemberId(String memberId);

    Optional<TenantMemberDTO> findTenantMemberByUserId(String userId);

    TenantMemberDTO tenantMemberSignIn(TenantMemberSignInArg arg);
}
