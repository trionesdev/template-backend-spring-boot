package com.trionesdev.template.rest.boss.domains.bossuser.controller.ro;

import lombok.Data;

@Data
public class AccountSignInRO {
    private String account;
    private String password;
}
