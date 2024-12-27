package com.trionesdev.template.rest.tenant.domains.org.controller.ro.tenant;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class TenantMemberUpdateRO {
    @NotBlank
    private String username;
    private String phone;
    @NotBlank
    private String nickname;
    private List<String> departmentIds;
}
