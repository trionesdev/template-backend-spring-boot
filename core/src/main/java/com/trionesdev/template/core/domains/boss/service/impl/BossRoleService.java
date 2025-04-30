package com.trionesdev.template.core.domains.boss.service.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.trionesdev.commons.core.constant.IdentityConstants;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.template.core.domains.boss.dao.criteria.BossRoleCriteria;
import com.trionesdev.template.core.domains.boss.dao.criteria.BossRoleGrantCriteria;
import com.trionesdev.template.core.domains.boss.dao.po.BossRoleGrantPO;
import com.trionesdev.template.core.domains.boss.dao.po.BossRolePO;
import com.trionesdev.template.core.domains.boss.dto.role.BossRoleMemberDTO;
import com.trionesdev.template.core.domains.boss.dto.role.cmd.BossRoleGrantsCreateCmd;
import com.trionesdev.template.core.domains.boss.dto.role.cmd.BossRoleGrantsRemoveCmd;
import com.trionesdev.template.core.domains.boss.manager.impl.BossRoleManager;
import com.trionesdev.template.core.domains.boss.shared.enums.RoleSubjectType;
import com.trionesdev.template.core.domains.tenant.dto.TenantMemberDetailDTO;
import com.trionesdev.template.core.domains.tenant.provider.OrgProvider;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BossRoleService {
    private final BossRoleManager roleManager;
    private final OrgProvider orgProvider;

    public void create(BossRolePO role) {
        roleManager.create(role);
    }

    public void deleteById(String id) {
        roleManager.deleteById(id);
    }

    public void updateById(BossRolePO role) {
        roleManager.updateById(role);
    }

    public Optional<BossRolePO> findById(String id) {
        return roleManager.findById(id);
    }

    public List<Tree<String>> findRoleTree() {
        var roles = roleManager.findList(BossRoleCriteria.builder().build());
        var treeNodes = roles.stream().map(role -> {
            var treeNode = new TreeNode<String>();
            var map = new HashMap<String, Object>();
            treeNode.setId(role.getId());
            treeNode.setParentId(role.getParentId());
            treeNode.setName(role.getName());
            treeNode.setExtra(map);
            return treeNode;
        }).collect(Collectors.toList());
        return TreeUtil.build(treeNodes, IdentityConstants.STRING_ID_ZERO_VALUE);
    }

    public void createRoleGrants(BossRoleGrantsCreateCmd cmd) {
        roleManager.roleGrant(cmd.getRoleId(), cmd.getSubjectType(), cmd.getSubjects());
    }

    public void removeRoleGrantBySubjects(BossRoleGrantsRemoveCmd cmd) {
        roleManager.removeRoleGrantBySubjects(cmd.getRoleId(), cmd.getSubjectType(), cmd.getSubjects());
    }

    private List<BossRoleMemberDTO> assembleRoleMembers(List<BossRoleGrantPO> records) {
        if (CollectionUtils.isEmpty(records)) {
            return new ArrayList<>();
        }
        var memberIds = records.stream().map(BossRoleGrantPO::getSubject).toList();
        var memberMap = orgProvider.getMembersByMemberIds(memberIds).stream().collect(Collectors.toMap(TenantMemberDetailDTO::getId, v -> v, (v1, v2) -> v1));
        return records.stream().map(t -> {
            var roleMember = BossRoleMemberDTO.builder()
                    .id(t.getId())
                    .roleId(t.getRoleId())
                    .memberId(t.getSubject())
                    .build();
            Optional.ofNullable(memberMap.get(t.getSubject())).ifPresent(member -> {
                roleMember.setNickname(member.getNickname());
                roleMember.setAvatar(member.getAvatar());
                roleMember.setUsername(member.getUsername());
                roleMember.setEmail(member.getEmail());
                roleMember.setPhone(member.getPhone());
            });
            return roleMember;
        }).collect(Collectors.toList());
    }

    public PageInfo<BossRoleMemberDTO> queryRoleMemberPage(BossRoleGrantCriteria criteria) {
        criteria.setSubjectType(RoleSubjectType.USER);
        var pageInfo = roleManager.findRoleGrantPage(criteria);
        return PageUtils.of(pageInfo, assembleRoleMembers(pageInfo.getRows()));
    }

}
