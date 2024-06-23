package com.trionesdev.mes.application.org.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.trionesdev.commons.core.constant.IdentityConstants;
import com.trionesdev.mes.domain.core.domains.org.repository.po.DepartmentPO;
import com.trionesdev.mes.domain.core.domains.org.service.DepartmentDomainService;
import com.trionesdev.mes.domain.core.domains.org.service.bo.DepartmentTreeArg;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DepartmentAppService {
    private final DepartmentDomainService departmentDomainService;

    public List<Tree<String>> departmentTree(DepartmentTreeArg arg) {
        return departmentDomainService.departmentTree(arg);
    }

    public List<Tree<String>> orgTree() {
        List<DepartmentPO> departments = departmentDomainService.findDepartments();
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

}
