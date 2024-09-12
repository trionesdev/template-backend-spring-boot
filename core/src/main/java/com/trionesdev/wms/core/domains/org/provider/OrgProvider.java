package com.trionesdev.wms.core.domains.org.provider;

import com.trionesdev.wms.core.domains.org.dto.*;

import java.util.Collection;
import java.util.List;

public interface OrgProvider {

    //region tenant
    TenantDTO getTenantById(String id);

    TenantDTO getActorTenant(String tenantId);

    TenantMemberDetailDTO getMemberByMemberId(String memberId);

    TenantMemberDetailDTO getMemberByUserId(String userId);

    List<TenantMemberDetailDTO> getTenantMembers(TenantMemberQuery query);

    List<TenantMemberDetailDTO> getMembersByMemberIds(Collection<String> memberIds);

    TenantMemberDetailDTO tenantMemberSignIn(TenantMemberSignInCmd arg);
    //endregion

    List<DepartmentDTO> getDepartmentsByIds(Collection<String> departmentIds);

}
