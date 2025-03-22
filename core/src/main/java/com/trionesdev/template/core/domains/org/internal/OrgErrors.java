package com.trionesdev.template.core.domains.org.internal;

import com.trionesdev.commons.exception.TrionesError;

public class OrgErrors {
    public static final TrionesError TENANT_ACCOUNT_OR_PWS_ERROR = TrionesError.builder().code("TENANT_ACCOUNT_OR_PWS_ERROR").message("账户或密码错误").build();
    public static final TrionesError PWD_ERROR = TrionesError.builder().code("PWD_ERROR").message("密码错误").build();
    public static final TrionesError TENANT_SERIAL_EMPTY = TrionesError.builder().code("TENANT_SERIAL_EMPTY").message("租户码不能为空").build();
    public static final TrionesError USER_NOT_FOUND = TrionesError.builder().code("USER_NOT_FOUND").message("用户不存在").build();
    public static final TrionesError VALIDATION_CODE_ERROR = TrionesError.builder().code("VALIDATION_CODE_ERROR").message("验证码错误").build();

}
