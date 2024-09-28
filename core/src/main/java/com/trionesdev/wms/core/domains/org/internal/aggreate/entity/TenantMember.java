package com.trionesdev.wms.core.domains.org.internal.aggreate.entity;

import lombok.Data;

@Data
public class TenantMember {

    private String id;
    private String tenantId;
    private String userId;
    private Boolean master;
    private String username;
    private String encodedPassword;
    private String nickname;

}
