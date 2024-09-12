package com.trionesdev.wms.core.domains.perm.service.impl;

import com.trionesdev.commons.context.actor.ActorContext;
import com.trionesdev.wms.core.domains.perm.dao.po.ResourceDraftPO;
import com.trionesdev.wms.core.domains.perm.internal.aggregate.entity.Resource;
import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.wms.core.domains.perm.manager.impl.ResourceManager;
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

    public List<Resource> findResourcesByClientType(ClientType clientType) {
        return resourceManager.findResourcesByClientType(clientType);
    }

}
