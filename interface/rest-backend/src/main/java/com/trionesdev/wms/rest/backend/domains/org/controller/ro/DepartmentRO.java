package com.trionesdev.wms.rest.backend.domains.org.controller.ro;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

public class DepartmentRO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
        @NonNull
        private String parentId;
        @NotBlank
        private String name;
        private String description;
    }

    @Data
    public static class Update {
        @NonNull
        private String parentId;
        @NotBlank
        private String name;
        private String description;
    }

}
