package com.trionesdev.template.core.domains.perm.service.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.trionesdev.commons.context.actor.ActorContext;
import com.trionesdev.commons.core.constant.IdentityConstants;
import com.trionesdev.template.core.domains.perm.dao.criteria.FunctionalResourceCriteria;
import com.trionesdev.template.core.domains.perm.dao.po.FunctionalResourceDraftPO;
import com.trionesdev.template.core.domains.perm.dto.FunctionalResourceDTO;
import com.trionesdev.template.core.domains.perm.internal.PermDomainConvert;
import com.trionesdev.template.core.domains.perm.internal.aggregate.entity.FunctionalResource;
import com.trionesdev.template.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.template.core.domains.perm.manager.impl.FunctionalResourceManager;
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
public class FunctionalResourceService {
    private final ActorContext actorContext;
    private final PermDomainConvert convert;
    private final FunctionalResourceManager functionalResourceManager;

    public void createResource(FunctionalResource resource) {
        functionalResourceManager.createResource(resource);
    }

    public void deleteResourceById(String id) {
        functionalResourceManager.deleteResourceById(id);
    }

    public void updateResourceById(FunctionalResource resource) {
        functionalResourceManager.updateResourceById(resource);
    }

    public Optional<FunctionalResourceDTO> findResourceById(String id) {
        return functionalResourceManager.findResourceById(id).map(convert::resourceEntityToDto);
    }

    public List<FunctionalResource> findResources(FunctionalResourceCriteria criteria) {
        return functionalResourceManager.findResources(criteria);
    }


    private List<TreeNode<String>> assembleTreeNodes(List<FunctionalResource> resources) {
        if (CollectionUtils.isEmpty(resources)) {
            return Collections.emptyList();
        }
        return resources.stream().map(resource -> {
            var map = new HashMap<String, Object>();
            map.put("uniqueCode", resource.getUniqueCode());
            map.put("type", resource.getType());
            map.put("groupCode", resource.getGroupCoe());
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

    public List<Tree<String>> findResourceTreeByClientType(String appIdentifier, ClientType clientType) {
        var nodes = assembleTreeNodes(functionalResourceManager.findResourcesByClientType(appIdentifier, clientType));
        return TreeUtil.build(nodes, IdentityConstants.STRING_ID_ZERO_VALUE);
    }

    public List<Tree<String>> findResourceTree(FunctionalResourceCriteria criteria) {
        var nodes = assembleTreeNodes(functionalResourceManager.findResources(criteria));
        return TreeUtil.build(nodes, IdentityConstants.STRING_ID_ZERO_VALUE);
    }


    public void createResourceDraft(FunctionalResourceDraftPO record) {
        functionalResourceManager.createResourceDraft(record);
    }

    public void deleteResourceDraftById(String id) {
        functionalResourceManager.deleteResourceDraftById(id);
    }

    public void updateDraftById(FunctionalResourceDraftPO record) {
        functionalResourceManager.updateDraftById(record);
    }

    public Optional<FunctionalResourceDraftPO> findDraftById(String id) {
        return functionalResourceManager.findDraftById(id);
    }

    public List<FunctionalResourceDraftPO> findDraftsByClientType(String appIdentifier, ClientType clientType) {
        return functionalResourceManager.findDraftsByClientType(appIdentifier, clientType);
    }


    public List<Tree<String>> findDraftTreeByClientType(String appIdentifier, ClientType clientType) {
        var resources = functionalResourceManager.findDraftsByClientType(appIdentifier, clientType).stream().map(resource -> {
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

    public void releaseDraft(String appIdentifier, ClientType clientType) {
        functionalResourceManager.releaseDraft(appIdentifier, clientType);
    }

    public List<FunctionalResource> findResourcesByClientType(String appIdentifier, ClientType clientType) {
        return functionalResourceManager.findResourcesByClientType(appIdentifier, clientType);
    }

//    public List<Tree<String>> findResourceTreeByClientType(String appIdentifier, ClientType clientType) {
//        var resources = functionalResourceManager.findResourcesByClientType(appIdentifier, clientType).stream().map(resource -> {
//            var map = new HashMap<String, Object>();
//            map.put("identifier", resource.getIdentifier());
//            map.put("type", resource.getType());
//            map.put("actions", resource.getActions());
//            var treeNode = new TreeNode<String>();
//            treeNode.setId(resource.getId());
//            treeNode.setParentId(resource.getParentId());
//            treeNode.setName(resource.getName());
//            treeNode.setExtra(map);
//            return treeNode;
//        }).collect(Collectors.toList());
//        if (CollectionUtils.isEmpty(resources)) {
//            return Collections.emptyList();
//        }
//        return TreeUtil.build(resources, IdentityConstants.STRING_ID_ZERO_VALUE);
//    }

}
