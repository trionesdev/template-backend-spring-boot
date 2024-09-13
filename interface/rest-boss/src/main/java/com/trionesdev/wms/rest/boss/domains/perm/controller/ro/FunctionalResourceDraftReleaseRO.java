package com.trionesdev.wms.rest.boss.domains.perm.controller.ro;

import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import lombok.Data;

@Data
public class FunctionalResourceDraftReleaseRO {
    private ClientType clientType;
}
