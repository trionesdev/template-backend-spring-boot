package com.trionesdev.template.core.domains.boss.service.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.trionesdev.commons.context.actor.ActorContext;
import com.trionesdev.commons.core.constant.IdentityConstants;
import com.trionesdev.template.core.domains.boss.dao.po.BossFunctionalResourceDraftPO;
import com.trionesdev.template.core.domains.boss.dao.po.BossFunctionalResourcePO;
import com.trionesdev.template.core.domains.boss.manager.impl.BossFunctionalResourceManager;
import com.trionesdev.template.core.domains.boss.shared.enums.ClientType;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BossFunctionalResourceService {
    private final ActorContext actorContext;

    private final BossFunctionalResourceManager functionalResourceManager;

    //region draft
    public void createResourceDraft(BossFunctionalResourceDraftPO resourceDraftPO) {
        functionalResourceManager.createResourceDraft(resourceDraftPO);
    }

    public void deleteResourceDraftById(String id) {
        functionalResourceManager.deleteResourceDraftById(id);
    }

    public void updateResourceDraftById(BossFunctionalResourceDraftPO record) {
        functionalResourceManager.updateResourceDraftById(record);
    }

    public Optional<BossFunctionalResourceDraftPO> findResourceDraftById(String id) {
        return functionalResourceManager.findResourceDraftById(id);
    }

    public List<Tree<String>> findResourceDraftTreeByClientType(ClientType clientType) {
        var resources = functionalResourceManager.findResourceDraftsByAppClient(clientType).stream().map(resource -> {
            var map = new HashMap<String, Object>();
            map.put("uniqueCode", resource.getUniqueCode());
            map.put("type", resource.getType());
            map.put("groupCode", resource.getGroupCode());
            map.put("icon", resource.getIcon());
            map.put("description", resource.getDescription());
            map.put("apiCode", resource.getApiCode());
            map.put("routePath", resource.getRoutePath());
            var treeNode = new TreeNode<String>();
            treeNode.setId(resource.getId());
            treeNode.setParentId(resource.getParentId());
            treeNode.setName(resource.getName());
            treeNode.setExtra(map);
            return treeNode;
        }).collect(Collectors.toList());
        return TreeUtil.build(resources, IdentityConstants.STRING_ID_ZERO_VALUE);
    }

    public void releaseResourceDraft(ClientType clientType) {
        functionalResourceManager.releaseResourceDraft(clientType);
    }

    public void syncResourceDraftFromRelease(ClientType clientType) {
        functionalResourceManager.syncResourceDraftFromRelease(clientType);
    }

    //endregion


    private List<TreeNode<String>> assembleTreeNodes(List<BossFunctionalResourcePO> resources) {
        if (CollectionUtils.isEmpty(resources)) {
            return Collections.emptyList();
        }
        return resources.stream().map(resource -> {
            var map = new HashMap<String, Object>();
            map.put("uniqueCode", resource.getUniqueCode());
            map.put("type", resource.getType());
            map.put("groupCode", resource.getGroupCode());
            map.put("icon", resource.getIcon());
            map.put("description", resource.getDescription());
            map.put("apiCode", resource.getApiCode());
            map.put("routePath", resource.getRoutePath());
            var treeNode = new TreeNode<String>();
            treeNode.setId(resource.getId());
            treeNode.setParentId(resource.getParentId());
            treeNode.setName(resource.getName());
            treeNode.setExtra(map);
            return treeNode;
        }).collect(Collectors.toList());
    }

    public List<Tree<String>> findResourceTreeByClientType(ClientType clientType) {
        var nodes = assembleTreeNodes(functionalResourceManager.findResourcesByClientType(clientType));
        return TreeUtil.build(nodes, IdentityConstants.STRING_ID_ZERO_VALUE);
    }


}
