package com.trionesdev.mes.core.domains.org.provider;

import com.trionesdev.mes.core.domains.org.dto.TenantDTO;
import com.trionesdev.mes.core.domains.org.dto.TenantMemberDetailDTO;
import com.trionesdev.mes.core.domains.org.dto.TenantMemberSignInCmd;

import java.util.Collection;
import java.util.List;

public interface OrgProvider {

    //region tenant
    TenantDTO getTenantById(String id);

    TenantDTO getActorTenant(String tenantId);

    TenantMemberDetailDTO getMemberByMemberId(String memberId);

    TenantMemberDetailDTO getMemberByUserId(String userId);

    List<TenantMemberDetailDTO> getMembersByMemberIds(Collection<String> memberIds);

    TenantMemberDetailDTO tenantMemberSignIn(TenantMemberSignInCmd arg);
    //endregion

}
