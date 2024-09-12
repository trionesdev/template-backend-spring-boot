package com.trionesdev.wms.core.domains.perm.manager.impl;

import com.trionesdev.wms.core.domains.perm.dao.impl.ResourceDraftDAO;
import com.trionesdev.wms.core.domains.perm.dao.po.ResourceDraftPO;
import com.trionesdev.wms.core.domains.perm.internal.PermDomainConvert;
import com.trionesdev.wms.core.domains.perm.internal.aggregate.entity.Resource;
import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.wms.core.domains.perm.repository.impl.ResourceRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ResourceManager {
    private final PermDomainConvert convert;
    private final ResourceDraftDAO resourceDraftDAO;
    private final ResourceRepository resourceRepository;

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

    /**
     * 发布草稿
     *
     * @param clientType
     */
    @Transactional
    public void releaseDraft(ClientType clientType) {
        resourceRepository.deleteByClientType(clientType);
        var drafts = resourceDraftDAO.selectListByClientType(clientType);
        if (CollectionUtils.isEmpty(drafts)) {
            return;
        }
        var resources = drafts.stream().map(convert::resourceDraftToEntity).collect(Collectors.toList());
        resourceRepository.saveBatch(resources);
    }

    public List<Resource> findResourcesByClientType(ClientType clientType) {
        return resourceRepository.findResourcesByClientType(clientType);
    }

}
