package com.trionesdev.mes.domain.core.dto.account;

import com.trionesdev.mes.domain.core.domains.user.internal.enums.GenderEnum;
import com.trionesdev.mes.domain.core.dto.tenant.TenantMemberDTO;
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
    private TenantMemberDTO tenantMember;
}
