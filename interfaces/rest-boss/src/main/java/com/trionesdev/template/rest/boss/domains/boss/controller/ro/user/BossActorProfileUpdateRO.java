package com.trionesdev.template.rest.boss.domains.boss.controller.ro.user;

import com.trionesdev.template.core.domains.boss.shared.enums.Gender;
import lombok.Data;

import java.time.Instant;

@Data
public class BossActorProfileUpdateRO {
    private String username;
    private String phone;
    private String email;
    private String avatar;
    private Gender gender;
    private String nickname;
    private Instant birthday;
}
