package com.trionesdev.mes.core.domains.org.dto;

import lombok.Data;

@Data
public class DepartmentDTO {
    private String id;
    private String parentId;
    private String name;
}
