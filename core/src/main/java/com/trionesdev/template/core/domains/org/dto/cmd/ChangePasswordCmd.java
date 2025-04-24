package com.trionesdev.template.core.domains.org.dto.cmd;

import lombok.Data;

@Data
public class ChangePasswordCmd {
    private String id;
    private String password;
}
