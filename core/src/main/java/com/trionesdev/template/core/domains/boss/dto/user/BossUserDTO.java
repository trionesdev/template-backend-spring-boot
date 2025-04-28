package com.trionesdev.template.core.domains.boss.dto.user;

import com.trionesdev.template.core.domains.boss.shared.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BossUserDTO {
    private String id;
    private String username;
    private String password;
    private String encodedPassword;
    private String phone;
    private String email;
    private String avatar;
    private Gender gender;
    private String nickname;
    private Instant birthday;
    private Boolean enabled;
}
