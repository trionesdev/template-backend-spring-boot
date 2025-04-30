package com.trionesdev.template.rest.boss.domains.boss.controller.ro.role;

import com.trionesdev.template.core.domains.boss.shared.enums.RoleSubjectType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class BossRoleGrantRemoveRO {
    @NotNull
    private RoleSubjectType grantObjType;
    @NotNull
    private List<String> grantObjIds;
}
