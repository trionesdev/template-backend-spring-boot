package com.trionesdev.template.rest.backend.domains.user.controller.impl;

import com.trionesdev.template.core.domains.user.service.impl.UserService;
import com.trionesdev.template.rest.backend.domains.user.controller.ro.AccountSignInRO;
import com.trionesdev.template.rest.backend.domains.user.controller.ro.SmsSignInRO;
import com.trionesdev.template.rest.backend.domains.user.controller.ro.SmsSignUpRO;
import com.trionesdev.template.rest.backend.domains.user.controller.vo.TokenVO;
import com.trionesdev.template.rest.backend.domains.user.internal.UserBeRestConvert;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.trionesdev.template.rest.backend.domains.user.internal.UserConstants.USER_PATH;

@Tag(name = "用户/用户账户")
@RequiredArgsConstructor
@RestController
@RequestMapping(USER_PATH)
public class UserAccountController {
    private final UserBeRestConvert convert;
    private final UserService userService;

    @Operation(summary = "账号登录")
    @PostMapping("sign-in/account")
    public TokenVO accountSignIn(@Validated @RequestBody AccountSignInRO args) {
        var cmd = convert.from(args);
        var token = userService.accountSignIn(cmd);
        return TokenVO.builder().token(token).build();
    }

    @Operation(summary = "短信登录")
    @PostMapping("sign-in/sms")
    public TokenVO smsSignIn(@Validated @RequestBody SmsSignInRO args) {
        var cmd = convert.from(args);
        var token = userService.smsSignIn(cmd);
        return TokenVO.builder().token(token).build();
    }

    @Operation(summary = "手机号码注册")
    @PostMapping("sign-up/phone")
    public void phoneSignUp(@Validated @RequestBody SmsSignUpRO args) {
        var cmd = convert.from(args);
        userService.phoneSignUp(cmd);
    }

}
