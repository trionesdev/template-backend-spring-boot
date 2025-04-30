package com.trionesdev.template.rest.boss.domains.boss.controller.ro.user;

import com.trionesdev.template.core.domains.boss.shared.enums.Gender;
import lombok.Data;

import java.time.Instant;

@Data
public class BossUserCreateRO {
    private String username;
    private String password;
    private String phone;
    private String email;
    private Gender gender;
    private String nickname;
    private Instant birthday;
}
