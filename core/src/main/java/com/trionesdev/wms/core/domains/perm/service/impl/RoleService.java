package com.trionesdev.wms.core.domains.perm.service.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.trionesdev.commons.core.constant.IdentityConstants;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.wms.core.domains.perm.dao.criteria.RoleCriteria;
import com.trionesdev.wms.core.domains.perm.dao.criteria.RoleGrantCriteria;
import com.trionesdev.wms.core.domains.perm.dao.po.RoleGrantPO;
import com.trionesdev.wms.core.domains.perm.dao.po.RolePO;
import com.trionesdev.wms.core.domains.perm.dto.RoleGrantCmd;
import com.trionesdev.wms.core.domains.perm.dto.RoleMemberDTO;
import com.trionesdev.wms.core.domains.perm.internal.enums.RoleGrantObjType;
import com.trionesdev.wms.core.domains.perm.manager.impl.RoleManager;
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
public class RoleService {
    private final RoleManager roleManager;

    public void create(RolePO role) {
        roleManager.create(role);
    }

    public void deleteById(String id) {
        roleManager.deleteById(id);
    }

    public void updateById(RolePO role) {
        roleManager.updateById(role);
    }

    public Optional<RolePO> findById(String id) {
        return roleManager.findById(id);
    }

    public List<Tree<String>> findRoleTree() {
        var roles = roleManager.findList(RoleCriteria.builder().build());
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

    public void roleGrant(RoleGrantCmd cmd) {
        roleManager.roleGrant(cmd.getRoleId(), cmd.getGrantObjType(), cmd.getGrantObjIds());
    }

    private List<RoleMemberDTO> assembleRoleMembers(List<RoleGrantPO> records) {
        if (CollectionUtils.isEmpty(records)) {
            return new ArrayList<>();
        }
        return records.stream().map(t -> {
            return RoleMemberDTO.builder().build();
        }).collect(Collectors.toList());
    }

    public PageInfo<RoleMemberDTO> queryRoleMemberPage(RoleGrantCriteria criteria) {
        criteria.setGrantObjType(RoleGrantObjType.MEMBER);
        var pageInfo = roleManager.findRoleGrantPage(criteria);
        return PageUtils.of(pageInfo, assembleRoleMembers(pageInfo.getRows()));
    }

}
