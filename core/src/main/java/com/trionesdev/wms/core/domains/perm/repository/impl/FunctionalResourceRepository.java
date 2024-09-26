package com.trionesdev.wms.core.domains.perm.repository.impl;

import com.google.common.collect.Lists;
import com.trionesdev.commons.exception.BusinessException;
import com.trionesdev.wms.core.domains.perm.dao.criteria.FunctionalResourceCriteria;
import com.trionesdev.wms.core.domains.perm.dao.impl.FunctionalResourceActionDAO;
import com.trionesdev.wms.core.domains.perm.dao.impl.FunctionalResourceDAO;
import com.trionesdev.wms.core.domains.perm.dao.impl.FunctionalResourceObjectDAO;
import com.trionesdev.wms.core.domains.perm.dao.po.FunctionalResourceActionPO;
import com.trionesdev.wms.core.domains.perm.dao.po.FunctionalResourceObjectPO;
import com.trionesdev.wms.core.domains.perm.dao.po.FunctionalResourcePO;
import com.trionesdev.wms.core.domains.perm.internal.PermDomainConvert;
import com.trionesdev.wms.core.domains.perm.internal.aggregate.entity.FunctionalResource;
import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class FunctionalResourceRepository {
    private final PermDomainConvert convert;
    private final FunctionalResourceDAO functionalResourceDAO;
    private final FunctionalResourceObjectDAO resourceObjectDAO;
    private final FunctionalResourceActionDAO resourceActionDAO;

    public void save(FunctionalResource resource) {
        var resourcePo = convert.resourceEntityToPo(resource);
        functionalResourceDAO.save(resourcePo);
    }

    public void updateById(FunctionalResource resource) {
        var resourcePo = convert.resourceEntityToPo(resource);
        functionalResourceDAO.updateById(resourcePo);
    }

    public void deleteById(String id) {
        var children = functionalResourceDAO.selectListByParentId(id);
        if (CollectionUtils.isNotEmpty(children)) {
            throw new BusinessException("HAS_CHILDREN_RESOURCE");
        }
        functionalResourceDAO.removeById(id);
    }

    public Optional<FunctionalResource> findById(String id) {
        return Optional.ofNullable(functionalResourceDAO.getById(id)).map(convert::resourcePoToEntity);
    }

    private List<FunctionalResource> assembleResources(List<FunctionalResourcePO> records) {
        if (CollectionUtils.isEmpty(records)) {
            return Lists.newArrayList();
        }
        return records.stream().map(convert::resourcePoToEntity).collect(Collectors.toList());
    }

    public List<FunctionalResource> findList(FunctionalResourceCriteria criteria) {
        return assembleResources(functionalResourceDAO.selectList(criteria));
    }

    public List<FunctionalResource> findListByClientType(String appCode, ClientType clientType) {
        return assembleResources(functionalResourceDAO.selectListByClientType(appCode, clientType));
    }

    public void saveBatch(List<FunctionalResource> resources) {
        List<FunctionalResourceObjectPO> objs = Lists.newArrayList();
        List<FunctionalResourceActionPO> actions = Lists.newArrayList();
        resources.forEach(draft -> {
            var resource = convert.resourceObjectEntityToPo(draft);
            objs.add(resource);
            if (CollectionUtils.isNotEmpty(draft.getActions())) {
                draft.getActions().forEach(action -> {
                    var act = convert.resourceActionEntityToPo(action);
                    act.setAppIdentifier(resource.getAppIdentifier());
                    act.setObjectId(resource.getId());
                    act.setClientType(resource.getClientType());
                    actions.add(act);
                });
            }
        });
        if (CollectionUtils.isNotEmpty(objs)) {
            resourceObjectDAO.saveBatch(objs);
        }
        if (CollectionUtils.isNotEmpty(actions)) {
            resourceActionDAO.saveBatch(actions);
        }
    }

    public void deleteByClientType(String appIdentifier, ClientType clientType) {
        resourceObjectDAO.deleteByClientType(appIdentifier, clientType);
        resourceActionDAO.deleteByClientType(appIdentifier, clientType);
    }

    public List<FunctionalResource> findResourcesByClientType(String appIdentifier, ClientType clientType) {
        var objects = resourceObjectDAO.selectListByClientType(appIdentifier, clientType);
        var actionsMap = resourceActionDAO.selectListByClientType(appIdentifier, clientType).stream().collect(Collectors.groupingBy(FunctionalResourceActionPO::getObjectId));
        return objects.stream().map(object -> {
            var resource = convert.resourceObjectToResource(object);
            var actions = Optional.ofNullable(actionsMap.get(object.getId())).map(list -> list.stream().map(convert::resourceActionToInnerAction).collect(Collectors.toList())).orElse(new ArrayList<>());
            resource.setActions(actions);
            return resource;
        }).collect(Collectors.toList());
    }

}
