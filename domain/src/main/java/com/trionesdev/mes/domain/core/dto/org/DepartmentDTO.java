package com.trionesdev.mes.domain.core.dto.org;

import lombok.Data;

@Data
public class DepartmentDTO {
    private String id;
    private String parentId;
    private String name;
}
