package com.trionesdev.mes.rest.backend.domains.account.controller.ro;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AccountSignInRO {
    @NotBlank
    private String account;
    @NotBlank
    private String password;
}
