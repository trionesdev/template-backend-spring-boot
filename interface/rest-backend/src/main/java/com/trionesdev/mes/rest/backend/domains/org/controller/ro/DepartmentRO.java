package com.trionesdev.mes.rest.backend.domains.org.controller.ro;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;

public class DepartmentRO {

    @Data
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
