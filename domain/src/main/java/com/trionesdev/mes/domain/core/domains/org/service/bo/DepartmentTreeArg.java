package com.trionesdev.mes.domain.core.domains.org.service.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentTreeArg {
    private Mode mode;

    public enum Mode {
        TENANT_ROOT,
        TENANT_SIDEWAYS
    }
}
