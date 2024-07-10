package com.trionesdev.mes.core.domains.org.dto;

import lombok.Data;

@Data
public class TenantMemberDTO {
    private String id;
    private String tenantId;
    private String userId;
    private String phone;
    private String email;
    private String avatar;
    private String nickname;
    private String firstName;
    private String lastName;
    private String username;
}
