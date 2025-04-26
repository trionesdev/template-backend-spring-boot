package com.trionesdev.template.core.domains.tenant.dto.cmd;

import lombok.Data;

@Data
public class ChangePasswordCmd {
    private String id;
    private String password;
}
