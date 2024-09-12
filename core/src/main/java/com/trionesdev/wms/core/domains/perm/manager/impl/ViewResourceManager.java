package com.trionesdev.wms.core.domains.perm.manager.impl;

import com.trionesdev.wms.core.domains.perm.dao.impl.ResourceDraftDAO;
import com.trionesdev.wms.core.domains.perm.dao.po.ViewResourceDraftPO;
import com.trionesdev.wms.core.domains.perm.internal.PermDomainConvert;
import com.trionesdev.wms.core.domains.perm.internal.aggregate.entity.Resource;
import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.wms.core.domains.perm.repository.impl.ViewResourceRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ViewResourceManager {
    private final PermDomainConvert convert;
    private final ResourceDraftDAO resourceDraftDAO;
    private final ViewResourceRepository viewResourceRepository;

    public void createResourceDraft(ViewResourceDraftPO record) {
        resourceDraftDAO.save(record);
    }

    public void deleteResourceDraftById(String id) {
        resourceDraftDAO.removeById(id);
    }

    public void updateDraftById(ViewResourceDraftPO record) {
        resourceDraftDAO.updateById(record);
    }

    public List<ViewResourceDraftPO> findDraftsByClientType(ClientType clientType) {
        return resourceDraftDAO.selectListByClientType(clientType);
    }

    /**
     * 发布草稿
     *
     * @param clientType
     */
    @Transactional
    public void releaseDraft(ClientType clientType) {
        viewResourceRepository.deleteByClientType(clientType);
        var drafts = resourceDraftDAO.selectListByClientType(clientType);
        if (CollectionUtils.isEmpty(drafts)) {
            return;
        }
        var resources = drafts.stream().map(convert::resourceDraftToEntity).collect(Collectors.toList());
        viewResourceRepository.saveBatch(resources);
    }

    public List<Resource> findResourcesByClientType(ClientType clientType) {
        return viewResourceRepository.findResourcesByClientType(clientType);
    }

}
