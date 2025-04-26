package com.trionesdev.template.core.domains.boss.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.template.core.domains.boss.dao.criteria.BossDepartmentCriteria;
import com.trionesdev.template.core.domains.boss.dao.mapper.BossDepartmentMapper;
import com.trionesdev.template.core.domains.boss.dao.po.BossDepartmentPO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class BossDepartmentDAO extends ServiceImpl<BossDepartmentMapper, BossDepartmentPO> {
    private LambdaQueryWrapper<BossDepartmentPO> buildQueryWrapper(BossDepartmentCriteria criteria) {
        var queryWrapper = new LambdaQueryWrapper<BossDepartmentPO>();
        if (Objects.nonNull(criteria)) {
            queryWrapper.eq(StringUtils.isNotBlank(criteria.getParentId()), BossDepartmentPO::getParentId, criteria.getParentId())
                    .like(StringUtils.isNotBlank(criteria.getName()), BossDepartmentPO::getName, criteria.getName())
            ;
        }
        return queryWrapper;
    }

    public List<BossDepartmentPO> selectListByParentId(String parentId) {
        return lambdaQuery().eq(BossDepartmentPO::getParentId, parentId).list();
    }

    public List<BossDepartmentPO> selectList(BossDepartmentCriteria criteria) {
        return list(buildQueryWrapper(criteria));
    }

}
