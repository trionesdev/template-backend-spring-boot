package com.trionesdev.template.core.domains.tenant.service.impl;

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
import com.trionesdev.template.core.domains.tenant.dao.criteria.DepartmentMemberCriteria;
import com.trionesdev.template.core.domains.tenant.dao.impl.DepartmentDAO;
import com.trionesdev.template.core.domains.tenant.dao.impl.DepartmentMemberDAO;
import com.trionesdev.template.core.domains.tenant.dao.po.DepartmentMemberPO;
import com.trionesdev.template.core.domains.tenant.dao.po.DepartmentPO;
import com.trionesdev.template.core.domains.tenant.dto.*;
import com.trionesdev.template.core.domains.tenant.dto.cmd.DepartmentTreeQuery;
import com.trionesdev.template.core.domains.tenant.dto.cmd.OrgNodeQuery;
import com.trionesdev.template.core.domains.tenant.dto.cmd.SetMemberDepartmentsCmd;
import com.trionesdev.template.core.domains.tenant.internal.OrgDomainConvert;
import com.trionesdev.template.core.domains.tenant.internal.aggregate.entity.TenantMember;
import com.trionesdev.template.core.domains.tenant.shared.enums.OrgNodeType;
import com.trionesdev.template.core.domains.tenant.manager.impl.DepartmentManager;
import com.trionesdev.template.core.domains.tenant.manager.impl.TenantManager;
import com.trionesdev.template.core.domains.tenant.manager.impl.TenantMemberManager;
import com.trionesdev.template.core.domains.tenant.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DepartmentServiceLocal implements DepartmentService {
    private final ActorContext actorContext;
    private final OrgDomainConvert convert;
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
    public List<DepartmentDTO> findDepartmentPaths(String id) {
        var tenant = tenantManager.findActorTenant(actorContext.getTenantId()).orElse(null);
        if (Objects.isNull(tenant)) {
            return Collections.emptyList();
        }
        List<DepartmentDTO> departments = new ArrayList<>();
        var root = DepartmentDTO.builder().id(IdentityConstants.STRING_ID_ZERO_VALUE).name(tenant.getName()).build();
        departments.add(root);
        if (Objects.equals(IdentityConstants.STRING_ID_ZERO_VALUE, id)) {
            return departments;
        }
        var pathDepartments = departmentManager.findDepartmentById(id).map(t -> {
            var paths = t.getPrevIds();
            var parentPathDepartments = ListUtil.toList(departmentManager.findDepartmentsByIds(paths));
            parentPathDepartments.add(t);
            return parentPathDepartments.stream().map(convert::poToDto).collect(Collectors.toList());
        }).orElse(Collections.emptyList());
        departments.addAll(pathDepartments);
        return departments;
    }

    @Override
    public List<Tree<String>> departmentTree(DepartmentTreeQuery arg) {
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
            root.put("icon", tenant.getLogo());
            if (DepartmentTreeQuery.Mode.TENANT_SIDEWAYS.equals(arg.getMode())) {
                List<Tree<String>> result = List.of(root);
                if (CollectionUtils.isNotEmpty(departmentNodes)) {
                    result.addAll(departmentNodes);
                }
                return result;
            } else {
                root.setChildren(departmentNodes);
                return Collections.singletonList(root);
            }
        }
    }

    @Override
    public void setMemberDepartments(SetMemberDepartmentsCmd arg) {
        tenantMemberManager.findMemberById(arg.getMemberId()).ifPresent(tenantMember -> {
            departmentMemberDAO.deleteByUserId(tenantMember.getUserId());
            if (CollectionUtil.isNotEmpty(arg.getDepartmentIds())) {
                List<DepartmentMemberPO> members = arg.getDepartmentIds().stream().map(t -> DepartmentMemberPO.builder().departmentId(t).memberId(tenantMember.getId()).build()).collect(Collectors.toList());
                departmentMemberDAO.saveBatch(members);
            }
        });

    }

    @Override
    public void deleteDepartmentMemberById(String id) {
        departmentManager.deleteDepartmentMemberById(id);
    }

    @Override
    public List<DepartmentMemberDTO> findDepartmentMembersByMemberId(String memberId) {
        return assembleDepartmentMembers(departmentManager.findDepartmentMembersByMemberId(memberId));
    }

    private List<DepartmentMemberDTO> assembleDepartmentMembers(List<DepartmentMemberPO> records) {
        if (CollectionUtil.isEmpty(records)) {
            return Collections.emptyList();
        }
        var memberIds = records.stream().map(DepartmentMemberPO::getMemberId).collect(Collectors.toSet());
        var membersMap = tenantMemberManager.findMembersByIds(memberIds).stream().collect(Collectors.toMap(TenantMember::getId, v -> v, (v1, v2) -> v1));
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
    public List<OrgNodeDTO> findDepartmentOrgNodes(OrgNodeQuery cmd) {
        List<OrgNodeDTO> result = new ArrayList<>();
        var departments = departmentManager.findDepartmentsByParentId(cmd.getDepartmentId());
        departments.forEach(t -> {
            result.add(OrgNodeDTO.builder().id(t.getId()).name(t.getName()).type(OrgNodeType.DEPARTMENT).build());
        });
        if (Objects.equals(OrgNodeType.DEPARTMENT, cmd.getType())) {
            return result;
        }
        var departmentMembers = departmentManager.findDepartmentMembersByDepartmentId(cmd.getDepartmentId());
        if (CollectionUtil.isNotEmpty(departmentMembers)) {
            var userIds = departmentMembers.stream().map(DepartmentMemberPO::getMemberId).collect(Collectors.toSet());
            var members = tenantMemberManager.findMembersByIds(userIds);
            members.forEach(t -> {
                var name = t.getName();
                if (StringUtils.isBlank(name)) {
                    name = t.getNickname();
                }
                result.add(OrgNodeDTO.builder().id(t.getId()).name(name).type(OrgNodeType.MEMBER).avatar(t.getAvatar()).nickname(t.getNickname()).build());
            });
        }
        return result;
    }
}
