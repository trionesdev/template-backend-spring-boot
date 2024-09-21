package com.trionesdev.wms.core.domains.perm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RoleMemberDTO {
    private String id;
    private String roleId;
    private String memberId;
    private String nickname;
    private String avatar;
    private String phone;
    private String email;
}
