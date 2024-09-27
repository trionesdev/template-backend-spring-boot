package com.trionesdev.wms.core.domains.perm.dto;

import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.wms.core.domains.perm.internal.enums.PermissionSubjectType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PolicySaveCmd {
    private String appCode;
    private ClientType clientType;
    private PermissionSubjectType subjectType;
    private String subject;
    private Set<PermissionDTO> permissions;
}
