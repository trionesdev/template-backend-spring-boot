package com.trionesdev.mes.core.domains.org.dto;

import lombok.Data;

import java.util.List;

@Data
public class TenantMemberDetailDTO {
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
    private List<String> departmentIds;
}
