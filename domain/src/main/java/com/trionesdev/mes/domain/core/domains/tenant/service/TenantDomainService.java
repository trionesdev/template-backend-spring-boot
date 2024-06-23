package com.trionesdev.mes.domain.core.domains.tenant.service;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.mes.domain.core.domains.tenant.entity.TenantMember;
import com.trionesdev.mes.domain.core.domains.tenant.repository.criteria.TenantMemberCriteria;
import com.trionesdev.mes.domain.core.domains.tenant.repository.po.TenantPO;
import com.trionesdev.mes.domain.core.dto.tenant.TenantDTO;
import com.trionesdev.mes.domain.core.dto.tenant.TenantMemberSignInArg;
import com.trionesdev.mes.domain.core.dto.tenant.TenantMemberDTO;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface TenantDomainService {

    void createTenant(TenantPO tenantPO);

    void updateTenantById(TenantPO tenantPO);

    Optional<TenantDTO> findTenantById(String id);

    Optional<TenantDTO> findActorTenant();

    void createTenantMember(TenantMember tenantMember);

    void updateTenantMemberById(TenantMember tenantMember);

    List<TenantMemberDTO> findTenantMembersByMemberIds(Collection<String> memberIds);

    Optional<TenantMemberDTO> findTenantMemberByMemberId(String memberId);

    Optional<TenantMemberDTO> findTenantMemberByUserId(String userId);

    PageInfo<TenantMemberDTO> findTenantMembersPage(TenantMemberCriteria criteria);

    TenantMemberDTO tenantMemberSignIn(TenantMemberSignInArg arg);
}
