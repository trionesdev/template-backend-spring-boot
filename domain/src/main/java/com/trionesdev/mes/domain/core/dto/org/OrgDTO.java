package com.trionesdev.mes.domain.core.dto.org;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class OrgDTO {
    private String id;
    private String name;
    private String avatar;
    private Type type;

    public enum Type{
        DEPARTMENT,
        MEMBER
    }
}
