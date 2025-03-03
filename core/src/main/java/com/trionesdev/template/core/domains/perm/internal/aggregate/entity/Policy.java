package com.trionesdev.template.core.domains.perm.internal.aggregate.entity;


import com.trionesdev.template.core.domains.perm.shared.enums.ClientType;
import com.trionesdev.template.core.domains.perm.shared.enums.PermissionSubjectType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Policy {
    private String appCode;
    private ClientType clientType;
    private PermissionSubjectType subjectType;
    private String subject;
    private Set<Permission> permissions;
}
