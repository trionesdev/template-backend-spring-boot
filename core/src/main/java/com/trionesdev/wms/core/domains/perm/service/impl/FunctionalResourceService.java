package com.trionesdev.wms.core.domains.perm.service.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.trionesdev.commons.context.actor.ActorContext;
import com.trionesdev.commons.core.constant.IdentityConstants;
import com.trionesdev.wms.core.domains.perm.dao.po.FunctionalResourceDraftPO;
import com.trionesdev.wms.core.domains.perm.internal.aggregate.entity.FunctionalResource;
import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.wms.core.domains.perm.manager.impl.FunctionalResourceManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FunctionalResourceService {
    private final ActorContext actorContext;
    private final FunctionalResourceManager functionalResourceManager;

    public void createResourceDraft(FunctionalResourceDraftPO record) {
        functionalResourceManager.createResourceDraft(record);
    }

    public void deleteResourceDraftById(String id) {
        functionalResourceManager.deleteResourceDraftById(id);
    }

    public void updateDraftById(FunctionalResourceDraftPO record) {
        functionalResourceManager.updateDraftById(record);
    }

    public List<FunctionalResourceDraftPO> findDraftsByClientType(ClientType clientType) {
        return functionalResourceManager.findDraftsByClientType(clientType);
    }

    public void releaseDraft(ClientType clientType) {
        functionalResourceManager.releaseDraft(clientType);
    }

    public List<FunctionalResource> findResourcesByClientType(ClientType clientType) {
        return functionalResourceManager.findResourcesByClientType(clientType);
    }

    public List<Tree<String>> findResourceTreeByClientType(ClientType clientType) {
        var resources = functionalResourceManager.findResourcesByClientType(clientType).stream().map(resource -> {
            var map = new HashMap<String, Object>();
            map.put("identifier", resource.getIdentifier());
            map.put("type", resource.getType());
            map.put("actions", resource.getActions());
            var treeNode = new TreeNode<String>();
            treeNode.setId(resource.getId());
            treeNode.setParentId(resource.getParentId());
            treeNode.setName(resource.getName());
            treeNode.setExtra(map);
            return treeNode;
        }).collect(Collectors.toList());
        return TreeUtil.build(resources, IdentityConstants.STRING_ID_ZERO_VALUE);
    }

}
