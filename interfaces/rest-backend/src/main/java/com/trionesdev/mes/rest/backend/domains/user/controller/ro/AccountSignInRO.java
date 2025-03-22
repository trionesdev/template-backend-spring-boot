package com.trionesdev.template.rest.backend.domains.user.controller.ro;

import lombok.Data;

@Data
public class AccountSignInRO {
    private String account;
    private String password;
}
