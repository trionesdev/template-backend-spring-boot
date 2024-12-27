package com.trionesdev.template.core.domains.org.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class OrgNodeDTO {
    private String id;
    private String name;
    private String avatar;
    private Type type;
    private String nickname;

    public enum Type{
        DEPARTMENT,
        MEMBER
    }
}
