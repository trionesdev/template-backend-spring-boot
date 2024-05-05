package com.trionesdev.backend.domain.core.domains.user.dao.domain.enums;

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
