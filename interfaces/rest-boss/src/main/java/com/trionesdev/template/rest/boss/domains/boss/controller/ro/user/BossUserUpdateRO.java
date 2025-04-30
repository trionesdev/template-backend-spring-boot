package com.trionesdev.template.rest.boss.domains.boss.controller.ro.user;

import com.trionesdev.template.core.domains.boss.shared.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.Instant;

@Data
public class BossUserUpdateRO {
    @NotBlank
    private String username;
    private String phone;
    private String email;
    private Gender gender;
    @NotBlank
    private String nickname;
    private Instant birthday;
}
