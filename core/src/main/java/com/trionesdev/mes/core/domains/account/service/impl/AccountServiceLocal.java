package com.trionesdev.mes.core.domains.account.service.impl;

import cn.hutool.core.util.StrUtil;
import com.trionesdev.commons.context.actor.ActorContext;
import com.trionesdev.commons.context.actor.ActorRoleEnum;
import com.trionesdev.commons.core.jwt.JwtClaims;
import com.trionesdev.commons.core.jwt.JwtFacade;
import com.trionesdev.commons.exception.BusinessException;
import com.trionesdev.mes.core.domains.account.dto.ActorDTO;
import com.trionesdev.mes.core.domains.account.internal.AccountBeanConvert;
import com.trionesdev.mes.core.domains.account.service.AccountService;
import com.trionesdev.mes.core.domains.org.provider.OrgProvider;
import com.trionesdev.mes.core.domains.user.provider.UserProvider;
import com.trionesdev.mes.core.domains.org.dto.TenantMemberDetailDTO;
import com.trionesdev.mes.core.domains.org.dto.TenantMemberSignInCmd;
import com.trionesdev.mes.core.domains.user.dto.AccountSignInCmd;
import com.trionesdev.mes.core.domains.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class AccountServiceLocal implements AccountService {
    private final JwtFacade jwtFacade;
    private final ActorContext actorContext;
    private final AccountBeanConvert convert;
    private final UserProvider userProvider;
    private final OrgProvider orgProvider;

    @Override
    public String accountSignIn(AccountSignInCmd args) {
        UserDTO user = userProvider.accountSignIn(args);
        if (Objects.isNull(user)){
            throw new BusinessException("ACCOUNT_OR_PASSWORD_ERROR");
        }
        return jwtFacade.generate(user.getId(), JwtClaims.builder().role(ActorRoleEnum.USER.name()).build());
    }

    @Override
    public String tenantMemberSignIn(TenantMemberSignInCmd arg) {
        TenantMemberDetailDTO tenantMember = orgProvider.tenantMemberSignIn(arg);
        return jwtFacade.generate(tenantMember.getUserId(), JwtClaims.builder().role(ActorRoleEnum.TENANT_MEMBER.name()).tenantId(tenantMember.getTenantId()).tenantMemberId(tenantMember.getId()).build());
    }

    @Override
    public ActorDTO actor() {
        if (StrUtil.isBlank(actorContext.getUserId())) {
            return null;
        }
        UserDTO user = userProvider.getUserById(actorContext.getUserId());
        ActorDTO actorDTO = convert.userToActor(user);
        actorDTO.setActorRole(actorContext.getRole());
        if (StrUtil.isNotBlank(actorContext.getMemberId())) {
            actorDTO.setTenantMember(orgProvider.getMemberByUserId(actorContext.getUserId()));
        }
        return actorDTO;
    }
}
