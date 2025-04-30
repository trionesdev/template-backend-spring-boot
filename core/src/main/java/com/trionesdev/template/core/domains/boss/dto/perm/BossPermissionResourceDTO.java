package com.trionesdev.template.core.domains.boss.dto.perm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BossPermissionResourceDTO {
    private String resourceCode;
    private String effect;
}
