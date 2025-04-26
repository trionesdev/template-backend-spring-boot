package com.trionesdev.template.core.domains.user.dto.cmd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@RequiredArgsConstructor
@AllArgsConstructor
public class SmsSignInCmd {
    private String phone;
    private String verificationCode;
}
