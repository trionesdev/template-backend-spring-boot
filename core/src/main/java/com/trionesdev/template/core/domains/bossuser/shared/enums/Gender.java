package com.trionesdev.template.core.domains.bossuser.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Gender {
    MALE("男"),
    FEMALE("女");
    @Getter
    private final String babel;
}
