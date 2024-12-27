package com.trionesdev.template.core.domains.perm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PolicyDTO {
    private Boolean master;
    private Set<PermissionDTO> permissions;
}
