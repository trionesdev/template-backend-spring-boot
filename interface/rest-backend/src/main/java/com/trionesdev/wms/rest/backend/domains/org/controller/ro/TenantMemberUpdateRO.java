package com.trionesdev.wms.rest.backend.domains.org.controller.ro;

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
