package com.trionesdev.mes.domain.core.domains.tenant.service.impl;

import com.trionesdev.commons.context.actor.ActorRoleEnum;
import com.trionesdev.commons.core.jwt.JwtFacade;
import com.trionesdev.commons.exception.BusinessException;
import com.trionesdev.mes.domain.core.domains.tenant.entity.TenantMember;
import com.trionesdev.mes.domain.core.domains.tenant.internal.TenantBeanConvert;
import com.trionesdev.mes.domain.core.domains.tenant.manager.impl.TenantManager;
import com.trionesdev.mes.domain.core.domains.tenant.manager.impl.TenantMemberManager;
import com.trionesdev.mes.domain.core.domains.tenant.repository.po.TenantPO;
import com.trionesdev.mes.domain.core.domains.tenant.service.TenantService;
import com.trionesdev.mes.domain.core.dto.tenant.TenantMemberSignInArg;
import com.trionesdev.mes.domain.core.dto.tenant.TenantMemberDTO;
import com.trionesdev.mes.domain.core.dto.user.UserBindDTO;
import com.trionesdev.mes.domain.core.provider.ssp.user.UserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TenantServiceLocal implements TenantService {
    private final JwtFacade jwtFacade;
    private final TenantBeanConvert convert;
    private final TenantManager tenantManager;
    private final TenantMemberManager tenantMemberManager;
    private final UserProvider userProvider;

    public void createTenant(TenantPO tenantPO) {
        tenantManager.createTenant(tenantPO);
    }

    public void updateTenantById(TenantPO tenantPO) {
        tenantManager.updateTenantById(tenantPO);
    }

    @Transactional
    @Override
    public void createTenantMember(TenantMember tenantMember) {
        var userDTO = UserBindDTO.builder().phone(tenantMember.getPhone()).build();
        var userId = userProvider.bindUser(userDTO);
        var tenantMemberPO = convert.entityToPO(tenantMember);
        tenantMemberPO.setUserId(userId);
        tenantMemberManager.createMember(tenantMemberPO);
    }

    @Override
    public List<TenantMemberDTO> findTenantMembersByMemberIds(Collection<String> memberIds) {
        var members = tenantMemberManager.findMembersByIds(memberIds);
        return members.stream().map(t -> {
            return convert.memberPOToDTO(t);
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<TenantMemberDTO> findTenantMemberByMemberId(String memberId) {
        return tenantMemberManager.findMemberById(memberId).map(convert::memberPOToDTO);
    }

    @Override
    public Optional<TenantMemberDTO> findTenantMemberByUserId(String userId) {
        return tenantMemberManager.findMemberByUserId(userId).map(convert::memberPOToDTO);
    }

    @Override
    public TenantMemberDTO tenantMemberSignIn(TenantMemberSignInArg arg) {
        return tenantManager.findTenantBySerial(arg.getTenantSerial())
                .map(tenantPO -> {
                    return tenantMemberManager.findMemberByUsername(arg.getTenantSerial(), arg.getUsername()).map(tenantMemberPO -> {
                        if (arg.passwordMatch(tenantMemberPO.getEncodedPassword())) {
                            return convert.memberPOToDTO(tenantMemberPO);
                        }
                        throw new BusinessException("ACCOUNT_OR_PASSWORD_ERROR");
                    }).orElseThrow(() -> new BusinessException("ACCOUNT_OR_PASSWORD_ERROR"));
                }).orElseThrow(() -> new BusinessException("TENANT_NOT_FOUND"));
    }
}
