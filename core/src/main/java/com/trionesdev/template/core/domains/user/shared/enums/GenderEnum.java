package com.trionesdev.template.core.domains.user.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GenderEnum {
    MALE("男"),
    FEMALE("女");
    private final String babel;
}
