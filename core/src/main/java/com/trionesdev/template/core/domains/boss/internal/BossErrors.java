package com.trionesdev.template.core.domains.boss.internal;

import com.trionesdev.commons.exception.TrionesError;

public class BossErrors {
    public static final TrionesError ACCOUNT_OR_PWD_ERROR = TrionesError.builder().code("ACCOUNT_OR_PWD_ERROR").message("账户名或密码错误").build();
    public static final TrionesError PWD_ERROR = TrionesError.builder().code("PWD_ERROR").message("密码错误").build();

}
