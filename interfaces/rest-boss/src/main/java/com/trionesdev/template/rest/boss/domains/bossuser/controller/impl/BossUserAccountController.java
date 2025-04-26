package com.trionesdev.template.rest.boss.domains.bossuser.controller.impl;

import com.trionesdev.template.core.domains.boss.service.impl.BossUserService;
import com.trionesdev.template.rest.boss.domains.bossuser.controller.ro.AccountSignInRO;
import com.trionesdev.template.rest.boss.domains.bossuser.controller.vo.TokenVO;
import com.trionesdev.template.rest.boss.domains.bossuser.internal.BossUserRestBossConvert;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.trionesdev.template.rest.boss.domains.bossuser.internal.BossUserConstants.USER_PATH;

@Tag(name = "用户")
@RequiredArgsConstructor
@RestController("boss_userAccountController")
@RequestMapping(USER_PATH)
public class BossUserAccountController {
    private final BossUserRestBossConvert convert;
    private final BossUserService bossUserService;

    @Operation(summary = "账号登录")
    @PostMapping("sign-in/account")
    public TokenVO accountSignIn(@Validated @RequestBody AccountSignInRO args) {
        var cmd = convert.accountSignInCmdFromRo(args);
        var token = bossUserService.accountSignIn(cmd);
        return TokenVO.builder().token(token).build();
    }
}
