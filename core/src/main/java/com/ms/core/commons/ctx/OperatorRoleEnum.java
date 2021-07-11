package com.ms.core.commons.ctx;

import com.google.common.base.Strings;

import java.util.Objects;

public enum OperatorRoleEnum {
    USER,
    ADMIN;

    public static OperatorRoleEnum getByName(String val) {
        if (!Strings.isNullOrEmpty(val)) {
            for (OperatorRoleEnum item : OperatorRoleEnum.values()) {
                if (Objects.equals(val, item.name())) {
                    return item;
                }
            }

        }
        return null;
    }
}
