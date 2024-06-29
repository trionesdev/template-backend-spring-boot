package com.trionesdev.mes.core.domains.account.dto;

import com.trionesdev.mes.core.domains.org.dto.TenantMemberDetailDTO;
import com.trionesdev.mes.core.domains.user.internal.enums.GenderEnum;
import lombok.Data;

import java.time.Instant;

@Data
public class ActorDTO {
    private String id;
    private String username;
    private String phone;
    private String email;
    private String avatar;
    private GenderEnum gender;
    private String firstName;
    private String lastName;
    private String nickname;
    private Instant birthday;
    private String actorRole;
    private TenantMemberDetailDTO tenantMember;
}