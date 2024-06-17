package com.trionesdev.mes.domain.core.domains.user.internal.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum GenderEnum {
    MALE("男"),
    FEMALE("女");
    @Getter
    private final String babel;
}
