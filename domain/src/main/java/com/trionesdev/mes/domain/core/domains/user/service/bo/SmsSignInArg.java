package com.trionesdev.mes.domain.core.domains.user.service.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@RequiredArgsConstructor
@AllArgsConstructor
public class SmsSignInArg {
    private String phone;
    private String captcha;
}
