package com.trionesdev.template.core.domains.perm.manager.impl;

import com.trionesdev.commons.core.constant.IdentityConstants;
import com.trionesdev.commons.exception.BusinessException;
import com.trionesdev.commons.exception.TrionesError;
import com.trionesdev.template.core.domains.perm.dao.criteria.FunctionalResourceCriteria;
import com.trionesdev.template.core.domains.perm.dao.impl.FunctionalResourceDAO;
import com.trionesdev.template.core.domains.perm.dao.impl.FunctionalResourceDraftDAO;
import com.trionesdev.template.core.domains.perm.dao.po.FunctionalResourceDraftPO;
import com.trionesdev.template.core.domains.perm.dao.po.FunctionalResourcePO;
import com.trionesdev.template.core.domains.perm.internal.PermDomainConvert;
import com.trionesdev.template.core.domains.perm.shared.enums.ClientType;
import com.trionesdev.template.core.domains.perm.shared.enums.FunctionalResourceType;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.trionesdev.template.core.domains.perm.internal.PermErrors.PARENT_RESOURCE_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class FunctionalResourceManager {
    private final PermDomainConvert convert;
    private final FunctionalResourceDraftDAO resourceDraftDAO;
    private final FunctionalResourceDAO resourceDAO;

    //region draft
    private void initialize(FunctionalResourceDraftPO parent, FunctionalResourceDraftPO record) {
        if (Objects.nonNull(parent)) {
            if (StringUtils.isNotBlank(parent.getGroupCode())) {
                record.setGroupCode(parent.getGroupCode());
            }
            if (Objects.equals(FunctionalResourceType.GROUP, record.getType())) {
                throw new BusinessException(TrionesError.builder().code("TYPE_ERROR").message("分组类型只能是根数据").build());
            }
            if (Objects.equals(FunctionalResourceType.MENU, record.getType()) && !CollectionUtils.containsAny(List.of(FunctionalResourceType.GROUP, FunctionalResourceType.MENU), parent.getType())) {
                throw new BusinessException(TrionesError.builder().code("TYPE_ERROR").message("菜单类型的父级类型只能是分组类型或菜单类型").build());
            }
            if (Objects.equals(FunctionalResourceType.RESOURCE, record.getType()) && !CollectionUtils.containsAny(List.of(FunctionalResourceType.GROUP, FunctionalResourceType.MENU), parent.getType())) {
                throw new BusinessException(TrionesError.builder().code("TYPE_ERROR").message("资源类型的父级类型只能是分组类型或菜单类型").build());
            }
            if (Objects.equals(FunctionalResourceType.ACTION, record.getType()) && !CollectionUtils.containsAny(List.of(FunctionalResourceType.GROUP, FunctionalResourceType.MENU, FunctionalResourceType.RESOURCE), parent.getType())) {
                throw new BusinessException(TrionesError.builder().code("TYPE_ERROR").message("操作类型的父级类型只能是分组类型或菜单类型或资源类型").build());
            }
        } else {
            if (Objects.equals(FunctionalResourceType.GROUP, record.getType())) {
                record.setGroupCode(record.getUniqueCode());
            }
        }
    }

    public void createResourceDraft(FunctionalResourceDraftPO resourceDraftPO) {
        FunctionalResourceDraftPO parent = null;
        if (!Objects.equals(IdentityConstants.STRING_ID_ZERO_VALUE, resourceDraftPO.getParentId()) && StringUtils.isNotBlank(resourceDraftPO.getParentId())) {
            parent = resourceDraftDAO.getById(resourceDraftPO.getParentId());
            if (Objects.isNull(parent)) {
                throw new BusinessException(PARENT_RESOURCE_NOT_FOUND);
            }
        }
        initialize(parent, resourceDraftPO);
        resourceDraftDAO.save(resourceDraftPO);
    }

    public void deleteResourceDraftById(String id) {
        resourceDraftDAO.removeById(id);
    }

    public void updateResourceDraftById(FunctionalResourceDraftPO resourceDraftPO) {
        resourceDraftDAO.updateById(resourceDraftPO);
    }

    public Optional<FunctionalResourceDraftPO> findResourceDraftById(String id) {
        return Optional.ofNullable(resourceDraftDAO.getById(id));
    }

    public List<FunctionalResourceDraftPO> findResourceDraftsByAppClient(String appCode, ClientType clientType) {
        return resourceDraftDAO.selectListByAppClient(appCode, clientType);
    }

    @Transactional
    public void releaseResourceDraft(String appCode, ClientType clientType) {
        resourceDAO.deleteByAppClient(appCode, clientType);
        List<FunctionalResourceDraftPO> resourceDrafts = findResourceDraftsByAppClient(appCode, clientType);
        if (CollectionUtils.isEmpty(resourceDrafts)) {
            return;
        }
        var resources = resourceDrafts.stream().map(resourceDraft -> {
            var resource = convert.resourceDraftToRelease(resourceDraft);
            return resource;
        }).toList();
        resourceDAO.saveBatch(resources);
    }

    /**
     * sync resources from release data to draft data
     *
     * @param appCode
     * @param clientType
     */
    @Transactional
    public void syncResourceDraftFromRelease(String appCode, ClientType clientType) {
        resourceDraftDAO.deleteByAppClient(appCode, clientType);
        List<FunctionalResourcePO> resources = resourceDAO.selectListByAppClient(appCode, clientType);
        if (CollectionUtils.isEmpty(resources)) {
            return;
        }
        var resourceDrafts = resources.stream().map(resource -> {
            var resourceDraft = convert.resourceReleaseToDraft(resource);
            return resourceDraft;
        }).toList();
        resourceDraftDAO.saveBatch(resourceDrafts);
    }

    //endregion


    public Optional<FunctionalResourcePO> findResourceById(String id) {
        return Optional.ofNullable(resourceDAO.getById(id));
    }

    public List<FunctionalResourcePO> findResources(FunctionalResourceCriteria criteria) {
        return resourceDAO.selectList(criteria);
    }

    public List<FunctionalResourcePO> findResourcesByClientType(String appCode, ClientType clientType) {
        return resourceDAO.selectListByAppClient(appCode, clientType);
    }


}
