package com.trionesdev.wms.core.domains.org.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Collection;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TenantMemberQuery {
    private OrGroup orGroup;


    @Data
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrGroup {
        private Collection<String> departmentIds;
        private Collection<String> ids;
    }
}
