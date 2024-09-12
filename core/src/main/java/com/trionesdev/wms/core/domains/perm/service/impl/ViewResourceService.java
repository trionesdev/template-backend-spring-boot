package com.trionesdev.wms.core.domains.perm.service.impl;

import com.trionesdev.commons.context.actor.ActorContext;
import com.trionesdev.wms.core.domains.perm.dao.po.ViewResourceDraftPO;
import com.trionesdev.wms.core.domains.perm.internal.aggregate.entity.Resource;
import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.wms.core.domains.perm.manager.impl.ViewResourceManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ViewResourceService {
    private final ActorContext actorContext;
    private final ViewResourceManager viewResourceManager;

    public void createResourceDraft(ViewResourceDraftPO record) {
        viewResourceManager.createResourceDraft(record);
    }

    public void deleteResourceDraftById(String id) {
        viewResourceManager.deleteResourceDraftById(id);
    }

    public void updateDraftById(ViewResourceDraftPO record) {
        viewResourceManager.updateDraftById(record);
    }

    public List<ViewResourceDraftPO> findDraftsByClientType(ClientType clientType) {
        return viewResourceManager.findDraftsByClientType(clientType);
    }

    public void releaseDraft(ClientType clientType) {
        viewResourceManager.releaseDraft(clientType);
    }

    public List<Resource> findResourcesByClientType(ClientType clientType) {
        return viewResourceManager.findResourcesByClientType(clientType);
    }

}
