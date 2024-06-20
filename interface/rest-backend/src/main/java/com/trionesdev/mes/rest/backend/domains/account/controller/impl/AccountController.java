package com.trionesdev.mes.rest.backend.domains.account.controller.impl;

import com.trionesdev.mes.domain.core.domains.user.service.UserService;
import com.trionesdev.mes.domain.core.domains.user.service.bo.AccountSignInArg;
import com.trionesdev.mes.rest.backend.domains.account.controller.ro.AccountSignInRO;
import com.trionesdev.mes.rest.backend.domains.account.controller.ro.SmsSignInRO;
import com.trionesdev.mes.rest.backend.domains.account.controller.vo.TokenVO;
import com.trionesdev.mes.rest.backend.domains.account.internal.AccountRestConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "账户")
@RequiredArgsConstructor
@RestController
@RequestMapping(AccountRestConstants.ACCOUNT_PATH)
public class AccountController {

    private final UserService userService;

    @Operation(summary = "账号登录")
    @PostMapping("sign-in/account")
    public TokenVO accountSinIn(@Validated @RequestBody AccountSignInRO args) {
        var signInArg = AccountSignInArg.builder().account(args.getAccount())
                .password(args.getPassword())
                .build();
        return TokenVO.builder().token(userService.accountSignIn(signInArg)).build();
    }

    @Operation(summary = "短信登录")
    @PostMapping("sign-in/sms")
    public TokenVO smsSinIn(@Validated @RequestBody SmsSignInRO args) {
        return TokenVO.builder().build();
    }

}
