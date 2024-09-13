package com.trionesdev.wms.core.domains.perm.manager.impl;

import com.trionesdev.wms.core.domains.perm.dao.impl.FunctionalResourceDraftDAO;
import com.trionesdev.wms.core.domains.perm.dao.po.FunctionalResourceDraftPO;
import com.trionesdev.wms.core.domains.perm.internal.PermDomainConvert;
import com.trionesdev.wms.core.domains.perm.internal.aggregate.entity.FunctionalResource;
import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.wms.core.domains.perm.repository.impl.FunctionalResourceRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FunctionalResourceManager {
    private final PermDomainConvert convert;
    private final FunctionalResourceDraftDAO resourceDraftDAO;
    private final FunctionalResourceRepository viewResourceRepository;

    public void createResourceDraft(FunctionalResourceDraftPO record) {
        resourceDraftDAO.save(record);
    }

    public void deleteResourceDraftById(String id) {
        resourceDraftDAO.removeById(id);
    }

    public void updateDraftById(FunctionalResourceDraftPO record) {
        resourceDraftDAO.updateById(record);
    }

    public List<FunctionalResourceDraftPO> findDraftsByClientType(ClientType clientType) {
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

    public List<FunctionalResource> findResourcesByClientType(ClientType clientType) {
        return viewResourceRepository.findResourcesByClientType(clientType);
    }

}
