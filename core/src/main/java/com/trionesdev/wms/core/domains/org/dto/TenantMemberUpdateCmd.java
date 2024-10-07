package com.trionesdev.wms.core.domains.org.dto;

import lombok.Data;

import java.util.List;

@Data
public class TenantMemberUpdateCmd {
    private String id;
    private String phone;
    private String email;
    private String avatar;
    private String nickname;
    private String firstName;
    private String lastName;
    private String username;
    private List<String> departmentIds;
}
