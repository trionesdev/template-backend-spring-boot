package com.trionesdev.template.core.domains.boss.dto.user.cmd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BossActorChangePasswordCmd {
    private String oldPassword;
    private String password;
}
