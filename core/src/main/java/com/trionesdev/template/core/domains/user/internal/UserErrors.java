package com.trionesdev.template.core.domains.user.internal;

import com.trionesdev.commons.exception.TrionesError;

public class UserErrors {
    public static final TrionesError PHONE_DUPLICATED = TrionesError.builder().code("PHONE_DUPLICATED").message("手机号码已存在").build();
    public static final TrionesError EMAIL_DUPLICATED = TrionesError.builder().code("EMAIL_DUPLICATED").message("邮箱已存在").build();
    public static final TrionesError USERNAME_DUPLICATED = TrionesError.builder().code("USERNAME_DUPLICATED").message("用户名已存在").build();
    public static final TrionesError ACCOUNT_OR_PWD_ERROR = TrionesError.builder().code("ACCOUNT_OR_PWD_ERROR").message("账户名或密码错误").build();
    public static final TrionesError VALIDATION_CODE_ERROR = TrionesError.builder().code("VALIDATION_CODE_ERROR").message("验证码错误").build();
    public static final TrionesError USER_NOT_FOUND = TrionesError.builder().code("USER_NOT_FOUND").message("用户不存在").build();
}
