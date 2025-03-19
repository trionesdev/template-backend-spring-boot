package com.trionesdev.template.core.domains.perm.shared.model;

import lombok.Data;

import java.util.Objects;

@Data
public class PermissionResource {
    private String resourceCode;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PermissionResource that = (PermissionResource) o;
        return Objects.equals(resourceCode, that.resourceCode);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(resourceCode);
    }
}
