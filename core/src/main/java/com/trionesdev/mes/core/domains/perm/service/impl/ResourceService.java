package com.trionesdev.mes.core.domains.perm.service.impl;

import com.trionesdev.commons.context.actor.ActorContext;
import com.trionesdev.mes.core.domains.perm.dao.po.ResourceDraftPO;
import com.trionesdev.mes.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.mes.core.domains.perm.manager.impl.ResourceManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ResourceService {
    private final ActorContext actorContext;
    private final ResourceManager resourceManager;

    public void createResourceDraft(ResourceDraftPO record) {
        resourceManager.createResourceDraft(record);
    }

    public void deleteResourceDraftById(String id) {
        resourceManager.deleteResourceDraftById(id);
    }

    public void updateDraftById(ResourceDraftPO record) {
        resourceManager.updateDraftById(record);
    }

    public List<ResourceDraftPO> findDraftsByClientType(ClientType clientType) {
        return resourceManager.findDraftsByClientType(clientType);
    }

    public void releaseDraft(ClientType clientType) {
        resourceManager.releaseDraft(clientType);
    }


}
