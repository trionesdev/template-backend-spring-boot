package com.trionesdev.mes.domain.core.domains.account.service.impl;

import cn.hutool.core.util.StrUtil;
import com.trionesdev.commons.context.actor.ActorContext;
import com.trionesdev.commons.context.actor.ActorRoleEnum;
import com.trionesdev.commons.core.jwt.JwtFacade;
import com.trionesdev.mes.domain.core.domains.account.internal.AccountBeanConvert;
import com.trionesdev.mes.domain.core.domains.account.service.AccountService;
import com.trionesdev.mes.domain.core.dto.account.ActorDTO;
import com.trionesdev.mes.domain.core.dto.tenant.TenantMemberDTO;
import com.trionesdev.mes.domain.core.dto.tenant.TenantMemberSignInArg;
import com.trionesdev.mes.domain.core.dto.user.AccountSignInArg;
import com.trionesdev.mes.domain.core.dto.user.UserDTO;
import com.trionesdev.mes.domain.core.provider.ssp.tenent.TenantProvider;
import com.trionesdev.mes.domain.core.provider.ssp.user.UserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountServiceLocal implements AccountService {
    private final JwtFacade jwtFacade;
    private final ActorContext actorContext;
    private final AccountBeanConvert convert;
    private final UserProvider userProvider;
    private final TenantProvider tenantProvider;

    @Override
    public String accountSignIn(AccountSignInArg args) {
        UserDTO user = userProvider.accountSignIn(args);
        return jwtFacade.generate(user.getId(), ActorRoleEnum.USER.name(), null, null);
    }

    @Override
    public String tenantMemberSignIn(TenantMemberSignInArg arg) {
        TenantMemberDTO tenantMember = tenantProvider.tenantMemberSignIn(arg);
        return jwtFacade.generate(tenantMember.getUserId(), ActorRoleEnum.TENANT_MEMBER.name(), tenantMember.getId(), tenantMember.getId());
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
            actorDTO.setTenantMember(tenantProvider.getMemberByUserId(actorContext.getUserId()));
        }
        return actorDTO;
    }
}
