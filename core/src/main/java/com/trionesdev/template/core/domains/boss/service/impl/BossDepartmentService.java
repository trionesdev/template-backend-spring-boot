package com.trionesdev.template.core.domains.boss.service.impl;

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
import com.trionesdev.template.core.domains.boss.dao.criteria.BossDepartmentMemberCriteria;
import com.trionesdev.template.core.domains.boss.dao.po.BossDepartmentMemberPO;
import com.trionesdev.template.core.domains.boss.dao.po.BossDepartmentPO;
import com.trionesdev.template.core.domains.boss.dto.department.BossDepartmentDTO;
import com.trionesdev.template.core.domains.boss.dto.department.BossDepartmentMemberDTO;
import com.trionesdev.template.core.domains.boss.dto.department.BossOrgNodeDTO;
import com.trionesdev.template.core.domains.boss.dto.department.cmd.BossOrgNodeQuery;
import com.trionesdev.template.core.domains.boss.internal.BossDepartmentDomainConvert;
import com.trionesdev.template.core.domains.boss.internal.BossUserDomainConvert;
import com.trionesdev.template.core.domains.boss.manager.impl.BossDepartmentManager;
import com.trionesdev.template.core.domains.boss.manager.impl.BossUserManager;
import com.trionesdev.template.core.domains.boss.repository.aggregate.entity.BossUser;
import com.trionesdev.template.core.domains.boss.shared.enums.OrgNodeType;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BossDepartmentService {
    private final ActorContext actorContext;
    private final BossDepartmentDomainConvert convert;
    private final BossUserDomainConvert userConvert;
    private final BossDepartmentManager departmentManager;
    private final BossUserManager userManager;


    public void createDepartment(BossDepartmentPO department) {
        departmentManager.createDepartment(department);
    }

    public void deleteDepartmentById(String id) {
        departmentManager.deleteDepartmentById(id);
    }

    public void updateDepartmentById(BossDepartmentPO department) {
        departmentManager.updateDepartmentById(department);
    }

    public Optional<BossDepartmentPO> findDepartmentById(String id) {
        return departmentManager.findDepartmentById(id);
    }

    public List<BossDepartmentPO> findDepartmentList() {
        return departmentManager.findDepartments();
    }

    public List<BossDepartmentPO> findDepartmentsByParentId(String parentId) {
        return departmentManager.findDepartmentsByParentId(parentId);
    }

    public List<Tree<String>> departmentTree() {
        var departments = departmentManager.findDepartments();
        List<TreeNode<String>> nodeList = CollUtil.newArrayList();
        departments.forEach(department -> {
            TreeNode<String> node = new TreeNode<>();
            node.setId(department.getId());
            node.setParentId(department.getParentId());
            node.setName(department.getName());
            nodeList.add(node);
        });
        return TreeUtil.build(nodeList, IdentityConstants.STRING_ID_ZERO_VALUE);
    }

    public List<BossDepartmentDTO> findDepartmentPaths(String id) {

        List<BossDepartmentDTO> departments = new ArrayList<>();
        if (Objects.equals(IdentityConstants.STRING_ID_ZERO_VALUE, id)) {
            return departments;
        }
        var pathDepartments = departmentManager.findDepartmentById(id).map(t -> {
            var paths = t.getPrevIds();
            var parentPathDepartments = ListUtil.toList(departmentManager.findDepartmentsByIds(paths));
            parentPathDepartments.add(t);
            return parentPathDepartments.stream().map(convert::departmentPoToDto).collect(Collectors.toList());
        }).orElse(Collections.emptyList());
        departments.addAll(pathDepartments);
        return departments;
    }


    // department member
    public void createDepartmentMember(BossDepartmentMemberPO dm) {
        departmentManager.createDepartmentMember(dm);
    }

    public void deleteDepartmentMemberById(String id) {
        departmentManager.deleteDepartmentMemberById(id);
    }

    public void updateDepartmentMemberById(BossDepartmentMemberPO dm) {
        departmentManager.updateDepartmentMemberById(dm);
    }

    private List<BossDepartmentMemberDTO> assembleDepartmentMembers(List<BossDepartmentMemberPO> records) {
        if (CollectionUtil.isEmpty(records)) {
            return Collections.emptyList();
        }
        var userIds = records.stream().map(BossDepartmentMemberPO::getUserId).collect(Collectors.toSet());
        var usersMap = userManager.findUserByIds(userIds).stream().collect(Collectors.toMap(BossUser::getId, v -> v, (v1, v2) -> v1));
        return records.stream().map(t -> {
            var depMember = convert.departmentMemberPoToDto(t);
            depMember.setMember(Optional.ofNullable(usersMap.get(t.getUserId())).map(userConvert::userEntityToDto).orElse(null));
            return depMember;
        }).collect(Collectors.toList());
    }

    public PageInfo<BossDepartmentMemberDTO> findDepartmentMembersPage(BossDepartmentMemberCriteria criteria) {
        var depMembersPage = departmentManager.findDepartmentMembersPage(criteria);
        return PageUtils.of(depMembersPage, assembleDepartmentMembers(depMembersPage.getRows()));
    }

    public List<BossOrgNodeDTO> findDepartmentOrgNodes(BossOrgNodeQuery cmd) {
        List<BossOrgNodeDTO> result = new ArrayList<>();
        var departments = departmentManager.findDepartmentsByParentId(cmd.getDepartmentId());
        departments.forEach(t -> {
            result.add(BossOrgNodeDTO.builder().id(t.getId()).name(t.getName()).type(OrgNodeType.DEPARTMENT).build());
        });
        if (Objects.equals(OrgNodeType.DEPARTMENT, cmd.getType())) {
            return result;
        }
        var departmentMembers = departmentManager.findDepartmentMembersByDepartmentId(cmd.getDepartmentId());
        if (CollectionUtil.isNotEmpty(departmentMembers)) {
            var userIds = departmentMembers.stream().map(BossDepartmentMemberPO::getUserId).collect(Collectors.toSet());
            var members = userManager.findUserByIds(userIds);
            members.forEach(t -> {
                var name = t.getNickname();
                if (StringUtils.isBlank(name)) {
                    name = t.getNickname();
                }
                result.add(BossOrgNodeDTO.builder().id(t.getId()).name(name).type(OrgNodeType.MEMBER).avatar(t.getAvatar()).nickname(t.getNickname()).build());
            });
        }
        return result;
    }

}
