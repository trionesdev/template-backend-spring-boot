package com.trionesdev.wms.rest.backend.domains.perm.controller.ro;

import lombok.Data;

@Data
public class RoleCreateRO {
    private String parentId = "0";
    private String name;
    private String description;
}
