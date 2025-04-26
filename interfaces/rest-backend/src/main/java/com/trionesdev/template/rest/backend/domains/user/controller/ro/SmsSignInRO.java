package com.trionesdev.template.rest.backend.domains.user.controller.ro;

import lombok.Data;

@Data
public class SmsSignInRO {
    private String phone;
    private String verificationCode;
}
