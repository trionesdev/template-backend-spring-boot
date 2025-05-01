package com.trionesdev.template.rest.boss.domains.boss.controller.ro.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BossActorChangePasswordRO {
    @NotBlank
    private String oldPassword;
    @NotBlank
    private String password;
}
