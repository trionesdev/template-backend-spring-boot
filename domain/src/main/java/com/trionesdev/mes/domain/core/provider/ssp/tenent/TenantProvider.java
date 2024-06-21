package com.trionesdev.mes.domain.core.provider.ssp.tenent;

import com.trionesdev.mes.domain.core.dto.tenant.TenantMemberDTO;
import com.trionesdev.mes.domain.core.dto.tenant.TenantMemberSignInArg;

import java.util.Collection;
import java.util.List;

public interface TenantProvider {

    TenantMemberDTO getMemberByMemberId(String memberId);
    TenantMemberDTO getMemberByUserId(String userId);

    List<TenantMemberDTO> getMembersByMemberIds(Collection<String> memberIds);

    TenantMemberDTO tenantMemberSignIn(TenantMemberSignInArg arg);

}
