package com.trionesdev.template.rest.boss.domains.perm.controller.ro;

import com.trionesdev.template.core.domains.perm.internal.enums.ClientType;
import lombok.Data;

@Data
public class FunctionalResourceDraftReleaseRO {
    private String appIdentifier;
    private ClientType clientType;
}
