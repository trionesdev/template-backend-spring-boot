package com.trionesdev.template.core.domains.boss.shared.model;

import lombok.Data;

import java.util.Objects;

@Data
public class BossPermissionResource {
    private String resourceCode;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BossPermissionResource that = (BossPermissionResource) o;
        return Objects.equals(resourceCode, that.resourceCode);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(resourceCode);
    }
}
