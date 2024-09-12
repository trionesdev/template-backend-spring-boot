package com.trionesdev.wms.core.domains.perm.internal.enums;

import io.swagger.v3.oas.annotations.media.Schema;

public enum ClientType {
    @Schema(description = "PC端WEB")
    PC_WEB,
    @Schema(description = "小程序")
    MINI_PROGRAM,
}
