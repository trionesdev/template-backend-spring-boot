package com.trionesdev.template.core.domains.bossuser.dto.cmd;

import com.trionesdev.template.core.domains.bossuser.shared.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BossUserUpdateCmd {
    private String id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String avatar;
    private Gender gender;
    private String nickname;
    private Instant birthday;
}
