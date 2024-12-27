package com.trionesdev.template.core.domains.org.provider;

import com.trionesdev.template.core.domains.org.dto.*;

import java.util.Collection;
import java.util.List;

public interface OrgProvider {

    //region tenant
    TenantDTO getTenantById(String id);

    TenantDTO getActorTenant(String tenantId);

    TenantMemberDTO getMemberById(String memberId);

    TenantMemberDetailDTO getMemberByUserId(String userId);

    List<TenantMemberDTO> getTenantMembers(TenantMemberQuery query);

    List<TenantMemberDetailDTO> getMembersByMemberIds(Collection<String> memberIds);

    List<TenantMemberDetailDTO> getMembersByUserIds(Collection<String> userIds);


    //endregion

    List<DepartmentDTO> getDepartmentsByIds(Collection<String> departmentIds);

}
