package com.trionesdev.mes.rest.backend.domains.org.controller.ro;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

public class TenantMemberRO {

    @Data
    public static class Create {
        @NotBlank
        private String username;
        @NotBlank
        private String password;
        private String phone;
        @NotBlank
        private String nickname;
        private List<String> departmentIds;
    }

    @Data
    public static class Update {
        @NotBlank
        private String username;
        private String phone;
        @NotBlank
        private String nickname;
        private List<String> departmentIds;
    }


}
