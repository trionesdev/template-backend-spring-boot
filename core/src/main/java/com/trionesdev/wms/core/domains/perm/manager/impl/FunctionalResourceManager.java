package com.trionesdev.wms.core.domains.perm.manager.impl;

import com.trionesdev.commons.core.constant.IdentityConstants;
import com.trionesdev.commons.exception.NotFoundException;
import com.trionesdev.wms.core.domains.perm.dao.criteria.FunctionalResourceCriteria;
import com.trionesdev.wms.core.domains.perm.dao.impl.FunctionalResourceDraftDAO;
import com.trionesdev.wms.core.domains.perm.dao.po.FunctionalResourceDraftPO;
import com.trionesdev.wms.core.domains.perm.internal.PermDomainConvert;
import com.trionesdev.wms.core.domains.perm.internal.aggregate.entity.FunctionalResource;
import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.wms.core.domains.perm.repository.impl.FunctionalResourceRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FunctionalResourceManager {
    private final PermDomainConvert convert;
    private final FunctionalResourceDraftDAO resourceDraftDAO;
    private final FunctionalResourceRepository functionalResourceRepository;

    public void createResource(FunctionalResource resource) {
        FunctionalResource parent = null;
        if (!Objects.equals(IdentityConstants.STRING_ID_ZERO_VALUE, resource.getParentId()) && StringUtils.isNotBlank(resource.getParentId())) {
            parent = functionalResourceRepository.findById(resource.getParentId()).orElseThrow(() -> new NotFoundException("PARENT_RESOURCE_NOT_FOUND"));
        }
        resource.initialize(parent);
        functionalResourceRepository.save(resource);
    }

    public void deleteResourceById(String id) {
        functionalResourceRepository.deleteById(id);
    }

    public void updateResourceById(FunctionalResource resource) {
        functionalResourceRepository.updateById(resource);
    }

    public Optional<FunctionalResource> findResourceById(String id) {
        return functionalResourceRepository.findById(id);
    }

    public List<FunctionalResource> findResources(FunctionalResourceCriteria criteria) {
        return functionalResourceRepository.findList(criteria);
    }

    public List<FunctionalResource> findResourcesByClientType(String appCode, ClientType clientType) {
        return functionalResourceRepository.findListByClientType(appCode, clientType);
    }




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



}
