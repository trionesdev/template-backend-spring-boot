package com.trionesdev.mes.core.domains.org.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.map.MapUtil;
import com.trionesdev.commons.context.actor.ActorContext;
import com.trionesdev.commons.core.constant.IdentityConstants;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.mes.core.domains.org.dao.impl.DepartmentDAO;
import com.trionesdev.mes.core.domains.org.dao.impl.DepartmentMemberDAO;
import com.trionesdev.mes.core.domains.org.dao.po.DepartmentMemberPO;
import com.trionesdev.mes.core.domains.org.dao.po.DepartmentPO;
import com.trionesdev.mes.core.domains.org.dao.po.TenantMemberPO;
import com.trionesdev.mes.core.domains.org.dto.OrgNodeDTO;
import com.trionesdev.mes.core.domains.org.dto.SetMemberDepartmentsArg;
import com.trionesdev.mes.core.domains.org.service.bo.DepartmentTreeArg;
import com.trionesdev.mes.core.domains.org.internal.OrgBeanConvert;
import com.trionesdev.mes.core.domains.org.manager.impl.DepartmentManager;
import com.trionesdev.mes.core.domains.org.dao.criteria.DepartmentMemberCriteria;
import com.trionesdev.mes.core.domains.org.manager.impl.TenantManager;
import com.trionesdev.mes.core.domains.org.manager.impl.TenantMemberManager;
import com.trionesdev.mes.core.domains.org.service.DepartmentService;
import com.trionesdev.mes.core.domains.org.dto.DepartmentDTO;
import com.trionesdev.mes.core.domains.org.dto.DepartmentMemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DepartmentServiceLocal implements DepartmentService {
    private final ActorContext actorContext;
    private final OrgBeanConvert convert;
    private final DepartmentManager departmentManager;
    private final TenantManager tenantManager;
    private final TenantMemberManager tenantMemberManager;
    private final DepartmentDAO departmentDAO;
    private final DepartmentMemberDAO departmentMemberDAO;


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
    public List<DepartmentPO> findDepartments() {
        return departmentDAO.list();
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
            var tenant = tenantManager.findActorTenant(actorContext.getTenantId()).orElse(null);
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
        departmentMemberDAO.deleteByMemberId(arg.getMemberId());
        if (CollectionUtil.isNotEmpty(arg.getDepartmentIds())) {
            List<DepartmentMemberPO> members = arg.getDepartmentIds().stream().map(t -> DepartmentMemberPO.builder().departmentId(t).memberId(arg.getMemberId()).build()).collect(Collectors.toList());
            departmentMemberDAO.saveBatch(members);
        }
    }

    @Override
    public List<DepartmentMemberDTO> findDepartmentMembersByMemberId(String memberId) {
        return assembleDepartmentMembers(departmentMemberDAO.selectListByMemberId(memberId));
    }

    private List<DepartmentMemberDTO> assembleDepartmentMembers(List<DepartmentMemberPO> records) {
        if (CollectionUtil.isEmpty(records)) {
            return Collections.emptyList();
        }
        var memberIds = records.stream().map(DepartmentMemberPO::getMemberId).collect(Collectors.toSet());
        var membersMap = tenantMemberManager.findMembersByIds(memberIds).stream().collect(Collectors.toMap(TenantMemberPO::getId, v -> v, (v1, v2) -> v1));
        return records.stream().map(t -> {
            var depMember = convert.poToDto(t);
            depMember.setMember(Optional.ofNullable(membersMap.get(t.getMemberId())).map(convert::memberPOToDTO).orElse(null));
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
        var departments = departmentManager.findDepartments();
        List<TreeNode<String>> nodeList = CollUtil.newArrayList();
        departments.forEach(department -> {
            Map<String, Object> extra = MapUtil.newHashMap();
            extra.put("type", "DEPARTMENT");
            TreeNode<String> node = new TreeNode<>();
            node.setId(department.getId());
            node.setParentId(department.getParentId());
            node.setName(department.getName());
            node.setExtra(extra);
            nodeList.add(node);
        });
        return TreeUtil.build(nodeList, IdentityConstants.STRING_ID_ZERO_VALUE);
    }

    @Override
    public List<OrgNodeDTO> orgList(String departmentId) {
        List<OrgNodeDTO> result = new ArrayList<>();
        var departments = departmentManager.findDepartmentsByParentId(departmentId);
        departments.forEach(t -> {
            result.add(OrgNodeDTO.builder().id(t.getId()).name(t.getName()).type(OrgNodeDTO.Type.DEPARTMENT).build());
        });
        var departmentMembers = departmentManager.findDepartmentMembersByDepartmentId(departmentId);
        if (CollectionUtil.isNotEmpty(departmentMembers)) {
            var memberIds = departmentMembers.stream().map(DepartmentMemberPO::getMemberId).collect(Collectors.toSet());
            var members = tenantMemberManager.findMembersByIds(memberIds);
            members.forEach(t -> {
                result.add(OrgNodeDTO.builder().id(t.getId()).name(t.getUsername()).type(OrgNodeDTO.Type.MEMBER).build());
            });
        }
        return result;
    }
}
