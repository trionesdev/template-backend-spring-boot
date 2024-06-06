package com.trionesdev.mes.rest.backend.domains.masterdata.controller.ro;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public class DefectiveRO {

    @Data
    public static class Create {
        private String code;
        @NotBlank
        private String name;
    }

    @Data
    public static class Update {
        @NotBlank
        private String name;
    }

}
