package com.trionesdev.template.core.domains.perm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PermissionResourceDTO {
    private String resourceCode;
    private String effect;
}
