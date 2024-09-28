package com.trionesdev.wms.core.domains.org.provider.impl;

import com.trionesdev.wms.core.domains.org.dto.*;
import com.trionesdev.wms.core.domains.org.manager.impl.DepartmentManager;
import com.trionesdev.wms.core.domains.org.manager.impl.TenantMemberManager;
import com.trionesdev.wms.core.domains.org.internal.OrgDomainConvert;
import com.trionesdev.wms.core.domains.org.manager.impl.TenantManager;
import com.trionesdev.wms.core.domains.org.provider.OrgProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class OrgProviderLocal implements OrgProvider {
    private final OrgDomainConvert convert;

    private final TenantManager tenantManager;
    private final TenantMemberManager tenantMemberManager;
    private final DepartmentManager departmentManager;

    @Override
    public TenantDTO getTenantById(String id) {
        return tenantManager.findTenantById(id).map(convert::tenantPoToDto).orElse(null);
    }

    @Override
    public TenantDTO getActorTenant(String tenantId) {
        return tenantManager.findActorTenant(tenantId).map(convert::tenantPoToDto).orElse(null);
    }

    @Override
    public TenantMemberDTO getMemberById(String memberId) {
        return tenantMemberManager.findMemberById(memberId).map(convert::memberEntityToDTO).orElse(null);
    }

    @Override
    public TenantMemberDetailDTO getMemberByUserId(String userId) {
        return tenantMemberManager.findMemberByUserId(userId).map(convert::memberPOToDTO).orElse(null);
    }

    @Override
    public List<TenantMemberDTO> getTenantMembers(TenantMemberQuery query) {
        var criteria = convert.tenantMemberQueryToCriteria(query);
        return tenantMemberManager.findMembers(criteria).stream().map(convert::memberEntityToDTO).collect(Collectors.toList());
    }


    @Override
    public List<TenantMemberDetailDTO> getMembersByMemberIds(Collection<String> memberIds) {
        return tenantMemberManager.findMembersByIds(memberIds).stream().map(convert::memberPOToDTO).toList();
    }


    @Override
    public List<DepartmentDTO> getDepartmentsByIds(Collection<String> departmentIds) {
        return departmentManager.findDepartmentsByIds(departmentIds).stream().map(convert::poToDto).toList();
    }
}
