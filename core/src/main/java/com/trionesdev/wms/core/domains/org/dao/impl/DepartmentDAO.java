package com.trionesdev.wms.core.domains.org.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.wms.core.domains.org.dao.criteria.DepartmentCriteria;
import com.trionesdev.wms.core.domains.org.dao.mapper.DepartmentMapper;
import com.trionesdev.wms.core.domains.org.dao.po.DepartmentPO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class DepartmentDAO extends ServiceImpl<DepartmentMapper, DepartmentPO> {
    private LambdaQueryWrapper<DepartmentPO> buildQueryWrapper(DepartmentCriteria criteria) {
        var queryWrapper = new LambdaQueryWrapper<DepartmentPO>();
        if (Objects.nonNull(criteria)) {
            queryWrapper.eq(StringUtils.isNotBlank(criteria.getParentId()), DepartmentPO::getParentId, criteria.getParentId())
                    .like(StringUtils.isNotBlank(criteria.getName()), DepartmentPO::getName, criteria.getName())
            ;
        }
        return queryWrapper;
    }

    public List<DepartmentPO> selectListByParentId(String parentId) {
        return lambdaQuery().eq(DepartmentPO::getParentId, parentId).list();
    }

    public List<DepartmentPO> selectList(DepartmentCriteria criteria) {
        return list(buildQueryWrapper(criteria));
    }

}
