package com.trionesdev.mes.core.domains.org.provider.impl;

import com.trionesdev.mes.core.domains.org.manager.impl.TenantMemberManager;
import com.trionesdev.mes.core.domains.org.dto.TenantDTO;
import com.trionesdev.mes.core.domains.org.dto.TenantMemberDTO;
import com.trionesdev.mes.core.domains.org.dto.TenantMemberSignInCmd;
import com.trionesdev.mes.core.domains.org.internal.OrgBeanConvert;
import com.trionesdev.mes.core.domains.org.manager.impl.TenantManager;
import com.trionesdev.mes.core.domains.org.provider.OrgProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Component
public class OrgProviderLocal implements OrgProvider {
    private final OrgBeanConvert convert;

    private final TenantManager tenantManager;
    private final TenantMemberManager tenantMemberManager;

    @Override
    public TenantDTO getTenantById(String id) {
        return tenantManager.findTenantById(id).map(convert::tenantPoToDto).orElse(null);
    }

    @Override
    public TenantDTO getActorTenant(String tenantId) {
        return tenantManager.findActorTenant(tenantId).map(convert::tenantPoToDto).orElse(null);
    }

    @Override
    public TenantMemberDTO getMemberByMemberId(String memberId) {
        return tenantMemberManager.findMemberById(memberId).map(convert::memberPOToDTO).orElse(null);
    }

    @Override
    public TenantMemberDTO getMemberByUserId(String userId) {
        return tenantMemberManager.findMemberByUserId(userId).map(convert::memberPOToDTO).orElse(null);
    }

    @Override
    public List<TenantMemberDTO> getMembersByMemberIds(Collection<String> memberIds) {
        return tenantMemberManager.findMembersByIds(memberIds).stream().map(convert::memberPOToDTO).toList();
    }

    @Override
    public TenantMemberDTO tenantMemberSignIn(TenantMemberSignInCmd arg) {
        return tenantMemberManager.accountSignIn(arg.getTenantSerial(), arg.getUsername(), arg.getPassword()).map(convert::memberPOToDTO).orElse(null);
    }
}
