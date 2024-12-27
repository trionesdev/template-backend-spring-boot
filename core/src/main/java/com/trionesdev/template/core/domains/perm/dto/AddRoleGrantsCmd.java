package com.trionesdev.template.core.domains.perm.dto;

import com.trionesdev.template.core.domains.perm.internal.enums.RoleSubjectType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AddRoleGrantsCmd {
    private String roleId;
    private RoleSubjectType grantObjType;
    private List<String> grantObjIds;
}
