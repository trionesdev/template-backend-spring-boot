package com.trionesdev.wms.core.domains.perm.repository.impl;

import com.google.common.collect.Lists;
import com.trionesdev.wms.core.domains.perm.dao.impl.FunctionalResourceActionDAO;
import com.trionesdev.wms.core.domains.perm.dao.impl.FunctionalResourceObjectDAO;
import com.trionesdev.wms.core.domains.perm.dao.po.FunctionalResourceActionPO;
import com.trionesdev.wms.core.domains.perm.dao.po.FunctionalResourceObjectPO;
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
    private final FunctionalResourceObjectDAO resourceObjectDAO;
    private final FunctionalResourceActionDAO resourceActionDAO;

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
