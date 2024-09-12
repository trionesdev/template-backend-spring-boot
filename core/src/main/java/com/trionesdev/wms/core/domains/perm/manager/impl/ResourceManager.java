package com.trionesdev.wms.core.domains.perm.manager.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.google.common.collect.Lists;
import com.trionesdev.wms.core.domains.perm.dao.impl.ResourceActionDAO;
import com.trionesdev.wms.core.domains.perm.dao.impl.ResourceObjectDAO;
import com.trionesdev.wms.core.domains.perm.dao.po.ResourceActionPO;
import com.trionesdev.wms.core.domains.perm.dao.po.ResourceDraftPO;
import com.trionesdev.wms.core.domains.perm.dao.impl.ResourceDraftDAO;
import com.trionesdev.wms.core.domains.perm.dao.po.ResourceObjectPO;
import com.trionesdev.wms.core.domains.perm.internal.PermBeanConvert;
import com.trionesdev.wms.core.domains.perm.internal.aggregate.entity.Resource;
import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ResourceManager {
    private final PermBeanConvert convert;
    private final ResourceDraftDAO resourceDraftDAO;
    private final ResourceObjectDAO resourceObjectDAO;
    private final ResourceActionDAO resourceActionDAO;

    public void createResourceDraft(ResourceDraftPO record) {
        resourceDraftDAO.save(record);
    }

    public void deleteResourceDraftById(String id) {
        resourceDraftDAO.removeById(id);
    }

    public void updateDraftById(ResourceDraftPO record) {
        resourceDraftDAO.updateById(record);
    }

    public List<ResourceDraftPO> findDraftsByClientType(ClientType clientType) {
        return resourceDraftDAO.selectListByClientType(clientType);
    }

    @Transactional
    public void releaseDraft(ClientType clientType) {
        resourceObjectDAO.deleteByClientType(clientType);
        resourceActionDAO.deleteByClientType(clientType);
        var drafts = resourceDraftDAO.selectListByClientType(clientType);
        if (CollectionUtils.isEmpty(drafts)) {
            return;
        }
        List<ResourceObjectPO> objs = Lists.newArrayList();
        List<ResourceActionPO> actions = Lists.newArrayList();
        drafts.forEach(draft -> {
            var resource = convert.resourceDraftToPO(draft);
            resource.setId(IdWorker.getIdStr());
            objs.add(resource);
            draft.getActions().forEach(action -> {
                var act = convert.resourceDraftActionToPO(action);
                act.setId(IdWorker.getIdStr());
                act.setResourceId(resource.getId());
                actions.add(act);
            });
        });
        if (CollectionUtils.isNotEmpty(objs)) {
            resourceObjectDAO.saveBatch(objs);
        }
        if (CollectionUtils.isNotEmpty(actions)) {
            resourceActionDAO.saveBatch(actions);
        }
    }

    public List<Resource> findResourcesByClientType(ClientType clientType) {
        return null;
    }

}
