package com.trionesdev.template.core.domains.org.provider.impl;

import com.trionesdev.commons.context.actor.ActorContext;
import com.trionesdev.template.core.domains.org.dto.*;
import com.trionesdev.template.core.domains.org.dto.cmd.TenantMemberQuery;
import com.trionesdev.template.core.domains.org.internal.OrgDomainConvert;
import com.trionesdev.template.core.domains.org.manager.impl.DepartmentManager;
import com.trionesdev.template.core.domains.org.manager.impl.TenantManager;
import com.trionesdev.template.core.domains.org.manager.impl.TenantMemberManager;
import com.trionesdev.template.core.domains.org.provider.OrgProvider;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class OrgProviderLocal implements OrgProvider {
    private final OrgDomainConvert convert;
    private final ActorContext actorContext;
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
    public List<TenantMemberDetailDTO> getMembersByUserIds(Collection<String> userIds) {
        return tenantMemberManager.findMembersByUserIds(userIds).stream().map(convert::memberPOToDTO).toList();
    }


    @Override
    public List<DepartmentDTO> getDepartmentsByIds(Collection<String> departmentIds) {
        return departmentManager.findDepartmentsByIds(departmentIds).stream().map(convert::poToDto).toList();
    }

    @Override
    public TenantDTO getCurrentTenant(String tenantId) {
        TenantDTO tenant = null;
        if (StringUtils.isNotBlank(tenantId)) {
            tenant = tenantManager.findTenantById(tenantId).map(convert::tenantPoToDto).orElse(null);
        }
        if (Objects.isNull(tenant)) {
            var members = tenantMemberManager.findMembersByUserId(actorContext.getUserId());
            if (CollectionUtils.isNotEmpty(members)) {
                tenant = tenantManager.findTenantById(members.get(0).getTenantId()).map(convert::tenantPoToDto).orElse(null);
            }
        }
        return tenant;
    }

    @Override
    public TenantMemberDTO getCurrentTenantMember(String tenantId,String userId) {
        TenantMemberDTO member = null;
        if (StringUtils.isNotBlank(tenantId)) {
            member = tenantMemberManager.findMemberByUserId(tenantId, actorContext.getUserId()).map(convert::memberEntityToDTO).orElse(null);
        }
        if (Objects.isNull(member)) {
            var members = tenantMemberManager.findMembersByUserId(userId);
            if (CollectionUtils.isNotEmpty(members)) {
                member = convert.memberEntityToDTO(members.get(0));
            }
        }
        return member;
    }
}
