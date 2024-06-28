package com.trionesdev.mes.core.domains.account.service;


import com.trionesdev.mes.core.domains.account.dto.ActorDTO;
import com.trionesdev.mes.core.domains.org.dto.TenantMemberSignInCmd;
import com.trionesdev.mes.core.domains.user.dto.AccountSignInCmd;

public interface AccountService {

    String accountSignIn(AccountSignInCmd args);

    String tenantMemberSignIn(TenantMemberSignInCmd arg);

    ActorDTO actor();
}
