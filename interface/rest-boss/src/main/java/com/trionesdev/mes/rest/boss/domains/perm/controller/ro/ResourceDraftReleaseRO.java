package com.trionesdev.mes.rest.boss.domains.perm.controller.ro;

import com.trionesdev.mes.core.domains.perm.internal.enums.ClientType;
import lombok.Data;

@Data
public class ResourceDraftReleaseRO {
    private ClientType clientType;
}
