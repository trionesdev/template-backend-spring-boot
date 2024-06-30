package com.trionesdev.mes.rest.backend.domains.masterdata.controller.ro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.trionesdev.mes.core.domains.masterdata.internal.enums.ProcessGrantObjType;
import com.trionesdev.mes.core.domains.masterdata.internal.enums.ProcessGrantType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

public class ManufactureProcessRO {

    @Data
    public static class Create {
        private String code;
        @NotBlank
        private String name;
        private BigDecimal ratio;
        private List<String> defectiveCodes;
        private Grant permissionGrant;
    }

    @Data
    public static class Update {
        @NotBlank
        private String name;
        private BigDecimal ratio;
        private List<String> defectiveCodes;
        private Grant permissionGrant;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Grant {
        private ProcessGrantType type;
        private List<GrantObj> assignees;
    }

    @Data
    public static class GrantObj {
        private String objId;
        private ProcessGrantObjType objType;
    }

}
