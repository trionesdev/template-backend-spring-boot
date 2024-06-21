package com.trionesdev.mes.domain.core.domains.org.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.trionesdev.commons.context.actor.ActorContext;
import com.trionesdev.commons.core.constant.IdentityConstants;
import com.trionesdev.mes.domain.core.domains.org.internal.OrgBeanConvert;
import com.trionesdev.mes.domain.core.domains.org.manager.impl.DepartmentManager;
import com.trionesdev.mes.domain.core.domains.org.repository.criteria.DepartmentMemberCriteria;
import com.trionesdev.mes.domain.core.domains.org.repository.po.DepartmentMemberPO;
import com.trionesdev.mes.domain.core.domains.org.repository.po.DepartmentPO;
import com.trionesdev.mes.domain.core.domains.org.service.DepartmentService;
import com.trionesdev.mes.domain.core.dto.org.DepartmentDTO;
import com.trionesdev.mes.domain.core.dto.org.DepartmentMemberDTO;
import com.trionesdev.mes.domain.core.dto.tenant.TenantMemberDTO;
import com.trionesdev.mes.domain.core.provider.ssp.tenent.TenantProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DepartmentServiceLocal implements DepartmentService {
    private final OrgBeanConvert convert;
    private final ActorContext actorContext;
    private final DepartmentManager departmentManager;
    private final TenantProvider tenantProvider;

    @Override
    public void createDepartment(DepartmentPO department) {
        departmentManager.createDepartment(department);
    }

    @Override
    public void deleteDepartmentById(String id) {
        departmentManager.deleteDepartmentById(id);
    }

    @Override
    public void updateDepartmentById(DepartmentPO department) {
        departmentManager.updateDepartmentById(department);
    }

    @Override
    public Optional<DepartmentDTO> findDepartmentById(String id) {
        return departmentManager.findDepartmentById(id).map(t -> {
            return convert.poToDto(t);
        });
    }

    @Override
    public List<Tree<String>> departmentTree() {
        var tenant = tenantProvider.getActorTenant();
        if (Objects.isNull(tenant)) {
            return Collections.emptyList();
        }
        Tree<String> root = new Tree<>();
        root.setId(IdentityConstants.STRING_ID_ZERO_VALUE);
        root.setParentId(tenant.getId());
        root.setName(tenant.getName());

        var departments = departmentManager.findDepartments();
        List<TreeNode<String>> nodeList = CollUtil.newArrayList();
        departments.forEach(department -> {
            TreeNode<String> node = new TreeNode<>();
            node.setId(department.getId());
            node.setParentId(department.getParentId());
            node.setName(department.getName());
            nodeList.add(node);
        });
        var departmentNodes = TreeUtil.build(nodeList, IdentityConstants.STRING_ID_ZERO_VALUE);
        root.setChildren(departmentNodes);
        return Collections.singletonList(root);
    }

    @Override
    public List<DepartmentMemberDTO> findMembersByDepartmentId(String departmentId) {
        var depMembers = departmentManager.findDepartmentMembers(DepartmentMemberCriteria.builder().departmentId(departmentId).build());
        var memberIds = depMembers.stream().map(DepartmentMemberPO::getMemberId).collect(Collectors.toSet());
        var membersMap = tenantProvider.getMembersByMemberIds(memberIds).stream().collect(Collectors.toMap(TenantMemberDTO::getId, v -> v, (v1, v2) -> v1));
        return depMembers.stream().map(t -> {
            var depMember = convert.poToDto(t);
            depMember.setMember(membersMap.get(t.getMemberId()));
            return depMember;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Tree<String>> orgTree() {
        return List.of();
    }
}
