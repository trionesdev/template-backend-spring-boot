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
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FunctionalResourceManager {
    private final PermDomainConvert convert;
    private final FunctionalResourceDraftDAO resourceDraftDAO;
    private final FunctionalResourceRepository functionalResourceRepository;

    public void createResourceDraft(FunctionalResourceDraftPO record) {
        resourceDraftDAO.save(record);
    }

    public void deleteResourceDraftById(String id) {
        resourceDraftDAO.removeById(id);
    }

    public void updateDraftById(FunctionalResourceDraftPO record) {
        resourceDraftDAO.updateById(record);
    }

    public Optional<FunctionalResourceDraftPO> findDraftById(String id) {
        return Optional.ofNullable(resourceDraftDAO.getById(id));
    }

    public List<FunctionalResourceDraftPO> findDraftsByClientType(String appIdentifier, ClientType clientType) {
        return resourceDraftDAO.selectListByClientType(appIdentifier, clientType);
    }

    /**
     * 发布草稿
     *
     * @param clientType
     */
    @Transactional
    public void releaseDraft(String appIdentifier, ClientType clientType) {
        functionalResourceRepository.deleteByClientType(appIdentifier, clientType);
        var drafts = resourceDraftDAO.selectListByClientType(appIdentifier, clientType);
        if (CollectionUtils.isEmpty(drafts)) {
            return;
        }
        var resources = drafts.stream().map(convert::resourceDraftToEntity).collect(Collectors.toList());
        functionalResourceRepository.saveBatch(resources);
    }

    public List<FunctionalResource> findResourcesByClientType(String appIdentifier, ClientType clientType) {
        return functionalResourceRepository.findResourcesByClientType(appIdentifier, clientType);
    }

}
