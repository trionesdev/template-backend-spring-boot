package com.trionesdev.template.core.domains.boss.dto.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BossRoleMemberDTO {
    private String id;
    private String roleId;
    private String memberId;
    private String username;
    private String nickname;
    private String avatar;
    private String phone;
    private String email;
}
