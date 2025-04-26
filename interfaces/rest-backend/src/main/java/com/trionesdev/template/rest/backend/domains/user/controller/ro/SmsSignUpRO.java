package com.trionesdev.template.rest.backend.domains.user.controller.ro;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SmsSignUpRO {
    @NotBlank
    private String phone;
    @NotBlank
    private String verificationCode;
}
