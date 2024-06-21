package com.trionesdev.mes.domain.core.domains.account.service;

import com.trionesdev.mes.domain.core.dto.account.ActorDTO;
import com.trionesdev.mes.domain.core.dto.tenant.TenantMemberSignInArg;
import com.trionesdev.mes.domain.core.dto.user.AccountSignInArg;

public interface AccountService {

    String accountSignIn(AccountSignInArg args);

    String tenantMemberSignIn(TenantMemberSignInArg arg);

    ActorDTO actor();
}
