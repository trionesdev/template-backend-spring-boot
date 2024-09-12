package com.trionesdev.wms.core.domains.perm.repository.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.google.common.collect.Lists;
import com.trionesdev.wms.core.domains.perm.dao.impl.ResourceActionDAO;
import com.trionesdev.wms.core.domains.perm.dao.impl.ResourceObjectDAO;
import com.trionesdev.wms.core.domains.perm.dao.po.ResourceActionPO;
import com.trionesdev.wms.core.domains.perm.dao.po.ResourceObjectPO;
import com.trionesdev.wms.core.domains.perm.internal.PermDomainConvert;
import com.trionesdev.wms.core.domains.perm.internal.aggregate.entity.Resource;
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
public class ResourceRepository {
    private final PermDomainConvert convert;
    private final ResourceObjectDAO resourceObjectDAO;
    private final ResourceActionDAO resourceActionDAO;

    public void saveBatch(List<Resource> resources){
        List<ResourceObjectPO> objs = Lists.newArrayList();
        List<ResourceActionPO> actions = Lists.newArrayList();
        resources.forEach(draft -> {
            var resource = convert.resourceObjectEntityToPo(draft);
            resource.setId(IdWorker.getIdStr());
            objs.add(resource);
            draft.getActions().forEach(action -> {
                var act = convert.resourceActionEntityToPo(action);
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

    public void deleteByClientType(ClientType clientType) {
        resourceObjectDAO.deleteByClientType(clientType);
        resourceActionDAO.deleteByClientType(clientType);
    }

    public List<Resource> findResourcesByClientType(ClientType clientType) {
        var objects = resourceObjectDAO.selectListByClientType(clientType);
        var actionsMap = resourceActionDAO.selectListByClientType(clientType).stream().collect(Collectors.groupingBy(ResourceActionPO::getResourceId));
        return objects.stream().map(object -> {
            var resource = convert.resourceObjectToResource(object);
            var actions = Optional.ofNullable(actionsMap.get(object.getId())).map(list -> list.stream().map(convert::resourceActionToInnerAction).collect(Collectors.toList())).orElse(new ArrayList<>());
            resource.setActions(actions);
            return resource;
        }).collect(Collectors.toList());
    }

}
