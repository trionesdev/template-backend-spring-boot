package com.trionesdev.template.core.domains.dic.service.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.trionesdev.template.core.domains.dic.dao.criteria.DistrictCriteria;
import com.trionesdev.template.core.domains.dic.dao.po.DistrictPO;
import com.trionesdev.template.core.domains.dic.manager.impl.DistrictManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DistrictService {
    private final DistrictManager districtManager;

    public Optional<DistrictPO> findDistrictById(String id) {
        return districtManager.findById(id);
    }

    public List<DistrictPO> findDistricts(DistrictCriteria criteria) {
        return districtManager.findDistricts(criteria);
    }

    public List<Tree<String>> queryDistrictTree() {
        var districts = districtManager.findDistricts(DistrictCriteria.builder().build());
        var treeNodes = districts.stream().map(district -> {
            var extraMap = new HashMap<String, Object>();
            extraMap.put("cityCode", district.getCode());
            extraMap.put("level", district.getLevel());
            var treeNode = new TreeNode<String>();
            treeNode.setId(district.getCode());
            treeNode.setParentId(district.getParentCode());
            treeNode.setName(district.getName());
            treeNode.setExtra(extraMap);
            return treeNode;
        }).collect(Collectors.toList());
        return TreeUtil.build(treeNodes, "100000");
    }
}
