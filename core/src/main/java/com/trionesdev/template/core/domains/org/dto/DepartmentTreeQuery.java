package com.trionesdev.template.core.domains.org.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentTreeQuery {
    private Mode mode;

    public enum Mode {
        TENANT_ROOT,
        TENANT_SIDEWAYS
    }
}
