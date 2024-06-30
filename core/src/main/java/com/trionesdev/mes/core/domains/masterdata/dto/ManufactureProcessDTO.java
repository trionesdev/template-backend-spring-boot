package com.trionesdev.mes.core.domains.masterdata.dto;

import com.trionesdev.mes.core.domains.masterdata.internal.enums.ProcessGrantObjType;
import com.trionesdev.mes.core.domains.masterdata.internal.enums.ProcessGrantType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ManufactureProcessDTO {
    private String id;
    private String code;
    private String name;
    private BigDecimal ratio;
    private List<String> defectiveCodes;
    private PermissionGrant permissionGrant;
    private List<Defective> defectives;

    @Data
    public static class PermissionGrant{
        private ProcessGrantType type;
        private List<GrantObj> assignees;
    }

    @Data
    public static class GrantObj {
        private String objId;
        private ProcessGrantObjType objType;
        private String name;
        private String avatar;
    }

    @Data
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Defective {
        private String code;
        private String name;
    }
}
