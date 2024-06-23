package com.trionesdev.mes.application.tenant.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.trionesdev.mes.domain.core.domains.org.service.DepartmentDomainService;
import com.trionesdev.mes.domain.core.domains.tenant.entity.TenantMember;
import com.trionesdev.mes.domain.core.domains.tenant.repository.po.TenantPO;
import com.trionesdev.mes.domain.core.domains.tenant.service.TenantDomainService;
import com.trionesdev.mes.domain.core.domains.user.entity.User;
import com.trionesdev.mes.domain.core.domains.user.service.UserDomainService;
import com.trionesdev.mes.domain.core.dto.org.DepartmentMemberDTO;
import com.trionesdev.mes.domain.core.dto.org.SetMemberDepartmentsArg;
import com.trionesdev.mes.domain.core.dto.tenant.TenantDTO;
import com.trionesdev.mes.domain.core.dto.tenant.TenantMemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TenantAppService {
    private final TenantDomainService tenantDomainService;
    private final UserDomainService userDomainService;
    private final DepartmentDomainService departmentDomainService;

    public void createTenant(TenantPO tenantPO) {
        tenantDomainService.createTenant(tenantPO);
    }

    public void updateTenantById(TenantPO tenantPO) {
        tenantDomainService.updateTenantById(tenantPO);
    }

    public Optional<TenantDTO> findTenantById(String id) {
        return tenantDomainService.findTenantById(id);
    }

    @Transactional
    public void createTenantMember(TenantMember tenantMember) {
        if (StrUtil.isNotBlank(tenantMember.getPhone())) {
            userDomainService.bindUser(User.builder().phone(tenantMember.getPhone()).build());
        }
        tenantDomainService.createTenantMember(tenantMember);
        if (CollectionUtil.isNotEmpty(tenantMember.getDepartmentIds())) {
            departmentDomainService.setMemberDepartments(SetMemberDepartmentsArg.builder().memberId(tenantMember.getId()).departmentIds(tenantMember.getDepartmentIds()).build());
        }
    }

    @Transactional
    public void updateTenantMemberById(TenantMember tenantMember) {
        tenantDomainService.updateTenantMemberById(tenantMember);
        departmentDomainService.setMemberDepartments(SetMemberDepartmentsArg.builder().memberId(tenantMember.getId()).departmentIds(tenantMember.getDepartmentIds()).build());
    }

    public Optional<TenantMemberDTO> findTenantMemberByMemberId(String memberId) {
        return tenantDomainService.findTenantMemberByMemberId(memberId).map(t -> {
            List<DepartmentMemberDTO> members = departmentDomainService.findDepartmentMembersByMemberId(memberId);
            t.setDepartmentIds(members.stream().map(DepartmentMemberDTO::getDepartmentId).toList());
            return t;
        });
    }

}
