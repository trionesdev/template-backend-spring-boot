package com.trionesdev.wms.core.domains.perm.internal.aggregate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Objects;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Permission {
    private String obj;
    private String act;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
         Permission that = (Permission) o;
        return Objects.equals(obj, that.act) && Objects.equals(act, that.act);
    }

    @Override
    public int hashCode() {
        return Objects.hash(obj, act);
    }
}
