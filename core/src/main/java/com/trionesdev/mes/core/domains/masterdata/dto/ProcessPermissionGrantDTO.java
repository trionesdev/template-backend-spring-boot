package com.trionesdev.mes.core.domains.masterdata.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProcessPermissionGrantDTO {
    private ProcessGrantType type;
    private List<GrantObj> assignees;

    public enum ProcessGrantType {
        ALL,
        ASSIGNEE
    }

    @Data
    public static class GrantObj {
        private String objId;
        private ProcessGrantObjType objType;
        private String name;
        private String avatar;
    }

    public enum ProcessGrantObjType {
        MEMBER,
        DEPARTMENT
    }

}
