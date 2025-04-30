package com.trionesdev.template.core.domains.boss.dto.department;


import com.trionesdev.template.core.domains.boss.shared.enums.OrgNodeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BossOrgNodeDTO {
    private String id;
    private String name;
    private String avatar;
    private OrgNodeType type;
    private String nickname;
}
