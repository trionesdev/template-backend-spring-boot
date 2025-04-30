package com.trionesdev.template.core.domains.boss.dto.perm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BossPolicyDTO {
    private Boolean master;
    private Set<BossPermissionResourceDTO> permissions;
}
