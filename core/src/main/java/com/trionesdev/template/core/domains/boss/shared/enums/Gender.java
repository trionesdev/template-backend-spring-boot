package com.trionesdev.template.core.domains.boss.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Gender {
    MALE("男"),
    FEMALE("女");
    @Getter
    private final String babel;
}
