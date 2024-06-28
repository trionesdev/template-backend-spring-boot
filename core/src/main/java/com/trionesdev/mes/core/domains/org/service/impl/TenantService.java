package com.trionesdev.mes.core.domains.org.service.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.mes.core.domains.org.dao.po.TenantMemberPO;
import com.trionesdev.mes.core.domains.org.manager.impl.TenantMemberManager;
import com.trionesdev.mes.core.domains.org.dao.criteria.TenantMemberCriteria;
import com.trionesdev.mes.core.domains.org.dto.TenantMemberDTO;
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
    public void createMember(TenantMemberDTO tenantMember) {
        var tenantMemberPO = convert.from(tenantMember);
        tenantMemberManager.createMember(tenantMemberPO);
        departmentManager.setMemberDepartments(tenantMember.getId(), tenantMember.getDepartmentIds());
    }

    @Transactional
    public void updateMemberById(TenantMemberDTO tenantMember) {
        var tenantMemberPO = convert.from(tenantMember);
        tenantMemberManager.updateMemberById(tenantMemberPO);
        departmentManager.setMemberDepartments(tenantMember.getId(), tenantMember.getDepartmentIds());
    }

    public Optional<TenantMemberDTO> findTenantMemberByMemberId(String memberId) {
        return tenantMemberManager.findMemberById(memberId).map(convert::memberPOToDTO);
    }

    private List<TenantMemberDTO> assembleTenantMembers(List<TenantMemberPO> members) {
        return members.stream().map(member -> {
            return convert.memberPOToDTO(member);
        }).collect(Collectors.toList());
    }

    public PageInfo<TenantMemberDTO> findTenantMembersPage(TenantMemberCriteria criteria) {
        var page = tenantMemberManager.findMembersPage(criteria);
        return PageUtils.of(page, assembleTenantMembers(page.getRows()));
    }

}
