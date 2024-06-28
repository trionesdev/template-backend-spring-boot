package com.trionesdev.mes.core.domains.org.provider;

import com.trionesdev.mes.core.domains.org.dto.TenantDTO;
import com.trionesdev.mes.core.domains.org.dto.TenantMemberDTO;
import com.trionesdev.mes.core.domains.org.dto.TenantMemberSignInCmd;

import java.util.Collection;
import java.util.List;

public interface OrgProvider {

    //region tenant
    TenantDTO getTenantById(String id);

    TenantDTO getActorTenant(String tenantId);

    TenantMemberDTO getMemberByMemberId(String memberId);

    TenantMemberDTO getMemberByUserId(String userId);

    List<TenantMemberDTO> getMembersByMemberIds(Collection<String> memberIds);

    TenantMemberDTO tenantMemberSignIn(TenantMemberSignInCmd arg);
    //endregion

}
