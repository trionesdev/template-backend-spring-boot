package com.trionesdev.mes.rest.backend.domains.account.controller.ro;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SmsSignInRO {
    @NotBlank
    private String phone;
    @NotBlank
    private String code;
}
