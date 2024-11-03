package com.trionesdev.wms.rest.tenant.domains.org.controller.ro.tenant;

import lombok.Data;

@Data
public class ActorMemberChangePasswordRO {
    private String oldPassword;
    private String newPassword;
}
