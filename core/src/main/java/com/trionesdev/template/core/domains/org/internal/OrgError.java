package com.trionesdev.template.core.domains.org.internal;

import com.trionesdev.commons.exception.TrionesError;

public class OrgError {
    public static final TrionesError TENANT_ACCOUNT_OR_PWS_ERROR = TrionesError.builder().code("TENANT_ACCOUNT_OR_PWS_ERROR").message("账户或密码错误").build();
    public static final TrionesError PWD_ERROR = TrionesError.builder().code("PWD_ERROR").message("密码错误").build();
}
