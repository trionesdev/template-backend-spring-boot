package com.trionesdev.mes.domain.core.domains.org.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.trionesdev.commons.context.actor.ActorContext;
import com.trionesdev.commons.core.constant.IdentityConstants;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.mes.domain.core.domains.org.internal.OrgBeanConvert;
import com.trionesdev.mes.domain.core.domains.org.manager.impl.DepartmentManager;
import com.trionesdev.mes.domain.core.domains.org.repository.criteria.DepartmentMemberCriteria;
import com.trionesdev.mes.domain.core.domains.org.repository.impl.DepartmentMemberRepository;
import com.trionesdev.mes.domain.core.domains.org.repository.impl.DepartmentRepository;
import com.trionesdev.mes.domain.core.domains.org.repository.mapper.DepartmentMapper;
import com.trionesdev.mes.domain.core.domains.org.repository.mapper.DepartmentMemberMapper;
import com.trionesdev.mes.domain.core.domains.org.repository.po.DepartmentMemberPO;
import com.trionesdev.mes.domain.core.domains.org.repository.po.DepartmentPO;
import com.trionesdev.mes.domain.core.domains.org.service.DepartmentDomainService;
import com.trionesdev.mes.domain.core.domains.org.service.bo.DepartmentTreeArg;
import com.trionesdev.mes.domain.core.dto.org.DepartmentDTO;
import com.trionesdev.mes.domain.core.dto.org.DepartmentMemberDTO;
import com.trionesdev.mes.domain.core.dto.org.SetMemberDepartmentsArg;
import com.trionesdev.mes.domain.core.dto.tenant.TenantMemberDTO;
import com.trionesdev.mes.domain.core.provider.ssp.tenent.TenantProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DepartmentDomainServiceLocal implements DepartmentDomainService {
    private final OrgBeanConvert convert;
    private final DepartmentManager departmentManager;
    private final TenantProvider tenantProvider;
    private final DepartmentRepository departmentRepository;
    private final DepartmentMemberRepository departmentMemberRepository;


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
    public List<Tree<String>> departmentTree(DepartmentTreeArg arg) {
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
        if (Objects.isNull(arg.getMode())) {
            return departmentNodes;
        } else {
            var tenant = tenantProvider.getActorTenant();
            if (Objects.isNull(tenant)) {
                return Collections.emptyList();
            }
            Tree<String> root = new Tree<>();
            root.setId(IdentityConstants.STRING_ID_ZERO_VALUE);
            root.setParentId(tenant.getId());
            root.setName(tenant.getName());
            if (DepartmentTreeArg.Mode.TENANT_SIDEWAYS.equals(arg.getMode())) {
                List<Tree<String>> result = ListUtil.toList(root);
                result.addAll(departmentNodes);
                return result;
            } else {
                root.setChildren(departmentNodes);
                return Collections.singletonList(root);
            }
        }
    }

    @Override
    public void setMemberDepartments(SetMemberDepartmentsArg arg) {
        departmentMemberRepository.deleteByMemberId(arg.getMemberId());
        if (CollectionUtil.isNotEmpty(arg.getDepartmentIds())) {
            List<DepartmentMemberPO> members = arg.getDepartmentIds().stream().map(t -> DepartmentMemberPO.builder().departmentId(t).memberId(arg.getMemberId()).build()).collect(Collectors.toList());
            departmentMemberRepository.saveBatch(members);
        }
    }

    @Override
    public List<DepartmentMemberDTO> findDepartmentMembersByMemberId(String memberId) {
        return assembleDepartmentMembers(departmentMemberRepository.selectListByMemberId(memberId));
    }

    private List<DepartmentMemberDTO> assembleDepartmentMembers(List<DepartmentMemberPO> records) {
        if (CollectionUtil.isEmpty(records)) {
            return Collections.emptyList();
        }
        var memberIds = records.stream().map(DepartmentMemberPO::getMemberId).collect(Collectors.toSet());
        var membersMap = tenantProvider.getMembersByMemberIds(memberIds).stream().collect(Collectors.toMap(TenantMemberDTO::getId, v -> v, (v1, v2) -> v1));
        return records.stream().map(t -> {
            var depMember = convert.poToDto(t);
            depMember.setMember(membersMap.get(t.getMemberId()));
            return depMember;
        }).collect(Collectors.toList());
    }

    @Override
    public List<DepartmentMemberDTO> findDepartmentMembers(DepartmentMemberCriteria criteria) {
        var depMembers = departmentManager.findDepartmentMembers(criteria);
        return assembleDepartmentMembers(depMembers);
    }

    @Override
    public PageInfo<DepartmentMemberDTO> findDepartmentMembersPage(DepartmentMemberCriteria criteria) {
        var depMembersPage = departmentManager.findDepartmentMembersPage(criteria);
        return PageUtils.of(depMembersPage, assembleDepartmentMembers(depMembersPage.getRows()));
    }

    @Override
    public List<Tree<String>> orgTree() {
        return List.of();
    }
}
