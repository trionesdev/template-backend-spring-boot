package com.trionesdev.wms.core.domains.org.service.impl;

import com.trionesdev.commons.context.actor.ActorRoleEnum;
import com.trionesdev.commons.core.jwt.JwtClaims;
import com.trionesdev.commons.core.jwt.JwtFacade;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.commons.exception.NotFoundException;
import com.trionesdev.wms.core.domains.org.dao.po.DepartmentMemberPO;
import com.trionesdev.wms.core.domains.org.dao.po.TenantMemberPO;
import com.trionesdev.wms.core.domains.org.dto.*;
import com.trionesdev.wms.core.domains.org.internal.aggreate.entity.TenantMember;
import com.trionesdev.wms.core.domains.org.manager.impl.TenantMemberManager;
import com.trionesdev.wms.core.domains.org.dao.criteria.TenantMemberCriteria;
import com.trionesdev.wms.core.domains.org.internal.OrgDomainConvert;
import com.trionesdev.wms.core.domains.org.manager.impl.DepartmentManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TenantService {
    private final OrgDomainConvert convert;
    private final JwtFacade jwtFacade;
    private final TenantMemberManager tenantMemberManager;
    private final DepartmentManager departmentManager;

    @Transactional
    public void createMember(TenantMemberCreateCmd cmd) {
        var tenantMember = convert.memberCreateCmdToEntity(cmd);
        tenantMemberManager.createMember(tenantMember);
        departmentManager.setMemberDepartments(tenantMember.getId(), tenantMember.getDepartmentIds());
    }

    @Transactional
    public void updateMemberById(TenantMemberUpdateCmd cmd) {
        var tenantMember = convert.memberUpdateCmdToEntity(cmd);
        tenantMemberManager.updateMemberById(tenantMember);
        departmentManager.setMemberDepartments(tenantMember.getId(), tenantMember.getDepartmentIds());
    }

    public void updateMemberProfileById(TenantMemberProfileUpdateCmd cmd) {
        var tenantMember = convert.memberProfileUpdateCmdToEntity(cmd);
        tenantMemberManager.updateMemberById(tenantMember);
    }

    private TenantMemberDTO assembleTenantMember(TenantMember tenantMember) {
        var tenantMemberDTO = convert.memberEntityToDTO(tenantMember);
        var depMembers = departmentManager.findDepartmentMembersByMemberId(tenantMemberDTO.getId());
        tenantMemberDTO.setDepartmentIds(depMembers.stream().map(DepartmentMemberPO::getDepartmentId).collect(Collectors.toList()));
        return tenantMemberDTO;
    }

    public Optional<TenantMemberDTO> findTenantMemberByMemberId(String memberId) {
        return tenantMemberManager.findMemberById(memberId).map(this::assembleTenantMember);
    }


    private List<TenantMemberDTO> assembleTenantMembers(List<TenantMember> members) {
        return members.stream().map(member -> {
            return convert.memberEntityToDTO(member);
        }).collect(Collectors.toList());
    }

    public List<TenantMemberDTO> findTenantMembers(TenantMemberCriteria criteria) {
        return assembleTenantMembers(tenantMemberManager.findMembers(criteria));
    }

    public PageInfo<TenantMemberDTO> findTenantMembersPage(TenantMemberCriteria criteria) {
        var page = tenantMemberManager.findMembersPage(criteria);
        return PageUtils.of(page, assembleTenantMembers(page.getRows()));
    }


    public String accountSignIn(TenantMemberSignInCmd cmd) {
        return tenantMemberManager.findByAccount(cmd.getTenantSerial(), cmd.getAccount(), cmd.getPassword()).map(tenantMember -> {
            return jwtFacade.generate(tenantMember.getId(), JwtClaims.builder().role(ActorRoleEnum.TENANT_MEMBER.name()).tenantId(tenantMember.getTenantId()).tenantMemberId(tenantMember.getId()).build());
        }).orElseThrow(() -> new NotFoundException("TENANT_ACCOUNT_OR_PWS_ERROR"));
    }

}
