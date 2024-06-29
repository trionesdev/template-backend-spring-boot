package com.trionesdev.mes.core.domains.org.service.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.mes.core.domains.org.dao.po.DepartmentMemberPO;
import com.trionesdev.mes.core.domains.org.dao.po.TenantMemberPO;
import com.trionesdev.mes.core.domains.org.dto.TenantMemberDetailDTO;
import com.trionesdev.mes.core.domains.org.manager.impl.TenantMemberManager;
import com.trionesdev.mes.core.domains.org.dao.criteria.TenantMemberCriteria;
import com.trionesdev.mes.core.domains.org.internal.OrgBeanConvert;
import com.trionesdev.mes.core.domains.org.manager.impl.DepartmentManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TenantService {
    private final OrgBeanConvert convert;
    private final TenantMemberManager tenantMemberManager;
    private final DepartmentManager departmentManager;

    @Transactional
    public void createMember(TenantMemberDetailDTO tenantMember) {
        var tenantMemberPO = convert.from(tenantMember);
        tenantMemberManager.createMember(tenantMemberPO);
        departmentManager.setMemberDepartments(tenantMember.getId(), tenantMember.getDepartmentIds());
    }

    @Transactional
    public void updateMemberById(TenantMemberDetailDTO tenantMember) {
        var tenantMemberPO = convert.from(tenantMember);
        tenantMemberManager.updateMemberById(tenantMemberPO);
        departmentManager.setMemberDepartments(tenantMember.getId(), tenantMember.getDepartmentIds());
    }

    public Optional<TenantMemberDetailDTO> findTenantMemberByMemberId(String memberId) {
        return tenantMemberManager.findMemberById(memberId).map(this::assembleTenantMember);
    }

    private TenantMemberDetailDTO assembleTenantMember(TenantMemberPO tenantMember) {
        var tenantMemberDTO = convert.memberPOToDTO(tenantMember);
        var depMembers = departmentManager.findDepartmentMembersByMemberId(tenantMemberDTO.getId());
        tenantMemberDTO.setDepartmentIds(depMembers.stream().map(DepartmentMemberPO::getDepartmentId).collect(Collectors.toList()));
        return tenantMemberDTO;
    }

    private List<TenantMemberDetailDTO> assembleTenantMembers(List<TenantMemberPO> members) {
        return members.stream().map(member -> {
            return convert.memberPOToDTO(member);
        }).collect(Collectors.toList());
    }

    public PageInfo<TenantMemberDetailDTO> findTenantMembersPage(TenantMemberCriteria criteria) {
        var page = tenantMemberManager.findMembersPage(criteria);
        return PageUtils.of(page, assembleTenantMembers(page.getRows()));
    }

}
