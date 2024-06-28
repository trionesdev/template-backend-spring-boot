package com.trionesdev.mes.core.domains.org.dto;

import lombok.Data;

import java.util.List;

@Data
public class TenantMemberCreateCmd {
    private String phone;
    private String email;
    private String nickname;
    private String avatar;
    private String firstName;
    private String lastName;
    private String username;
    private List<String> departmentIds;
}
