package com.trionesdev.wms.core.domains.perm.service.impl;

import com.trionesdev.commons.context.actor.ActorContext;
import com.trionesdev.wms.core.domains.perm.dao.po.FunctionalResourceDraftPO;
import com.trionesdev.wms.core.domains.perm.internal.aggregate.entity.FunctionalResource;
import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.wms.core.domains.perm.manager.impl.FunctionalResourceManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FunctionalResourceService {
    private final ActorContext actorContext;
    private final FunctionalResourceManager functionalResourceManager;

    public void createResourceDraft(FunctionalResourceDraftPO record) {
        functionalResourceManager.createResourceDraft(record);
    }

    public void deleteResourceDraftById(String id) {
        functionalResourceManager.deleteResourceDraftById(id);
    }

    public void updateDraftById(FunctionalResourceDraftPO record) {
        functionalResourceManager.updateDraftById(record);
    }

    public List<FunctionalResourceDraftPO> findDraftsByClientType(ClientType clientType) {
        return functionalResourceManager.findDraftsByClientType(clientType);
    }

    public void releaseDraft(ClientType clientType) {
        functionalResourceManager.releaseDraft(clientType);
    }

    public List<FunctionalResource> findResourcesByClientType(ClientType clientType) {
        return functionalResourceManager.findResourcesByClientType(clientType);
    }

}
