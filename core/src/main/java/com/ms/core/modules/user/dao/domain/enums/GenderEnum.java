package com.ms.core.modules.user.dao.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum GenderEnum {
    MALE("男"),
    FEMALE("女");
    @Getter
    @Setter
    private String babel;
}
