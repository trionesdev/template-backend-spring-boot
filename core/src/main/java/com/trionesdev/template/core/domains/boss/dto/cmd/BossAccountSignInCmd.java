package com.trionesdev.template.core.domains.boss.dto.cmd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@RequiredArgsConstructor
@AllArgsConstructor
public class BossAccountSignInCmd {
    private String account;
    private String password;
}
