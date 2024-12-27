package com.trionesdev.template.core.domains.org.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TenantMemberSignInCmd {
    private String tenantSerial;
    private String account;
    private String password;

}
