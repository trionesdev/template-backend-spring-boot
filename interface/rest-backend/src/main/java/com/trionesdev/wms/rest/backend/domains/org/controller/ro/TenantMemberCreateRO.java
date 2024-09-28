package com.trionesdev.wms.rest.backend.domains.org.controller.ro;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class TenantMemberCreateRO {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private String phone;
    @NotBlank
    private String nickname;
    private List<String> departmentIds;
}
