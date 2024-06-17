package com.trionesdev.mes.domain.core.domains.tenant.entity;

import lombok.Data;

@Data
public class TenantMember {
    private String id;
    private String tenantId;
    private String userId;
    private String phone;
    private String email;
    private String nickname;
    private String avatar;
    private String firstName;
    private String lastName;
    private String username;
}
