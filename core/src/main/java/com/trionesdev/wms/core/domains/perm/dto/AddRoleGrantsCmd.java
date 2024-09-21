package com.trionesdev.wms.core.domains.perm.dto;

import com.trionesdev.wms.core.domains.perm.internal.enums.RoleGrantObjType;
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
    private RoleGrantObjType grantObjType;
    private List<String> grantObjIds;
}
