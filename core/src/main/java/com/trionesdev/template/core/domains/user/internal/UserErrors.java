package com.trionesdev.template.core.domains.user.internal;

import com.trionesdev.commons.exception.TrionesError;

public class UserErrors {
    public static final TrionesError PHONE_EXISTS = TrionesError.builder() .code("PHONE_EXISTS").message("手机号码已存在").build();
}
