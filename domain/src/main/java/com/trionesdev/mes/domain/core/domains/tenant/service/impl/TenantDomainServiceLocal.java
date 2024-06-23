package com.trionesdev.mes.domain.core.domains.tenant.service.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.commons.exception.BusinessException;
import com.trionesdev.mes.domain.core.domains.tenant.entity.TenantMember;
import com.trionesdev.mes.domain.core.domains.tenant.internal.TenantBeanConvert;
import com.trionesdev.mes.domain.core.domains.tenant.repository.criteria.TenantMemberCriteria;
import com.trionesdev.mes.domain.core.domains.tenant.repository.impl.TenantMemberRepository;
import com.trionesdev.mes.domain.core.domains.tenant.repository.impl.TenantRepository;
import com.trionesdev.mes.domain.core.domains.tenant.repository.po.TenantMemberPO;
import com.trionesdev.mes.domain.core.domains.tenant.repository.po.TenantPO;
import com.trionesdev.mes.domain.core.domains.tenant.service.TenantDomainService;
import com.trionesdev.mes.domain.core.dto.tenant.TenantDTO;
import com.trionesdev.mes.domain.core.dto.tenant.TenantMemberDTO;
import com.trionesdev.mes.domain.core.dto.tenant.TenantMemberSignInArg;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TenantDomainServiceLocal implements TenantDomainService {
    private final TenantBeanConvert convert;
    private final TenantRepository tenantRepository;
    private final TenantMemberRepository tenantMemberRepository;


    public void createTenant(TenantPO tenantPO) {
        tenantRepository.save(tenantPO);
    }

    public void updateTenantById(TenantPO tenantPO) {
        tenantRepository.updateById(tenantPO);
    }

    @Override
    public Optional<TenantDTO> findTenantById(String id) {
        return Optional.ofNullable(tenantRepository.getById(id)).map(convert::tenantPoToDto);
    }

    @Override
    public Optional<TenantDTO> findActorTenant() {
        return Optional.ofNullable(tenantRepository.selectFirst()).map(convert::tenantPoToDto);
    }

    @Transactional
    @Override
    public void createTenantMember(TenantMember tenantMember) {
        var tenantMemberPO = convert.entityToPO(tenantMember);
        tenantMemberRepository.save(tenantMemberPO);
    }

    @Override
    public void updateTenantMemberById(TenantMember tenantMember) {
        var tenantMemberPO = convert.entityToPO(tenantMember);
        tenantMemberRepository.updateById(tenantMemberPO);
    }

    @Override
    public List<TenantMemberDTO> findTenantMembersByMemberIds(Collection<String> memberIds) {
        var members = tenantMemberRepository.listByIds(memberIds);
        return assembleTenantMembers(members);
    }

    @Override
    public Optional<TenantMemberDTO> findTenantMemberByMemberId(String memberId) {
        return Optional.ofNullable(tenantMemberRepository.getById(memberId)).map(convert::memberPOToDTO);
    }

    @Override
    public Optional<TenantMemberDTO> findTenantMemberByUserId(String userId) {
        return Optional.ofNullable(tenantMemberRepository.selectByUserId(userId)).map(convert::memberPOToDTO);
    }

    @Override
    public PageInfo<TenantMemberDTO> findTenantMembersPage(TenantMemberCriteria criteria) {
        var membersPage = tenantMemberRepository.selectPage(criteria);
        return PageUtils.of(membersPage, assembleTenantMembers(membersPage.getRows()));
    }

    private List<TenantMemberDTO> assembleTenantMembers(List<TenantMemberPO> members) {
        return members.stream().map(t -> {
            return convert.memberPOToDTO(t);
        }).collect(Collectors.toList());
    }

    @Override
    public TenantMemberDTO tenantMemberSignIn(TenantMemberSignInArg arg) {
        return Optional.ofNullable(tenantRepository.selectBySerial(arg.getTenantSerial()))
                .map(tenantPO -> {
                    return Optional.ofNullable(tenantMemberRepository.selectByUsername(arg.getTenantSerial(), arg.getUsername())).map(tenantMemberPO -> {
                        if (arg.passwordMatch(tenantMemberPO.getEncodedPassword())) {
                            return convert.memberPOToDTO(tenantMemberPO);
                        }
                        throw new BusinessException("ACCOUNT_OR_PASSWORD_ERROR");
                    }).orElseThrow(() -> new BusinessException("ACCOUNT_OR_PASSWORD_ERROR"));
                }).orElseThrow(() -> new BusinessException("TENANT_NOT_FOUND"));
    }
}
