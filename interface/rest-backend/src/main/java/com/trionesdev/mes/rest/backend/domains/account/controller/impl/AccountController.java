package com.trionesdev.mes.rest.backend.domains.account.controller.impl;

import com.trionesdev.mes.core.domains.account.service.AccountService;
import com.trionesdev.mes.domain.core.dto.account.ActorDTO;
import com.trionesdev.mes.core.domains.org.dto.TenantMemberSignInCmd;
import com.trionesdev.mes.core.domains.user.dto.AccountSignInCmd;
import com.trionesdev.mes.rest.backend.domains.account.controller.ro.AccountSignInRO;
import com.trionesdev.mes.rest.backend.domains.account.controller.ro.SmsSignInRO;
import com.trionesdev.mes.rest.backend.domains.account.controller.ro.TenantMemberSignInRO;
import com.trionesdev.mes.rest.backend.domains.account.controller.vo.TokenVO;
import com.trionesdev.mes.rest.backend.domains.account.internal.AccountRestConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "账户")
@RequiredArgsConstructor
@RestController
@RequestMapping(AccountRestConstants.ACCOUNT_PATH)
public class AccountController {

    private final AccountService accountService;

    @Operation(summary = "账号登录")
    @PostMapping("sign-in/account")
    public TokenVO accountSinIn(@Validated @RequestBody AccountSignInRO args) {
        var signInArg = AccountSignInCmd.builder().account(args.getAccount())
                .password(args.getPassword())
                .build();
        return TokenVO.builder().token(accountService.accountSignIn(signInArg)).build();
    }

    @Operation(summary = "短信登录")
    @PostMapping("sign-in/sms")
    public TokenVO smsSinIn(@Validated @RequestBody SmsSignInRO args) {
        return TokenVO.builder().build();
    }

    public TokenVO tenantMemberSignIn(@Validated @RequestBody TenantMemberSignInRO args){
        var tenantMemberSignInArg = TenantMemberSignInCmd.builder().tenantSerial(args.getTenantSerial())
                .username(args.getUsername())
                .password(args.getPassword())
                .build();
        return TokenVO.builder().token(accountService.tenantMemberSignIn(tenantMemberSignInArg)).build();
    }

    @Operation(summary = "获取当前登录用户信息")
    @GetMapping("actor")
    public ActorDTO actor() {
        return accountService.actor();
    }

}
