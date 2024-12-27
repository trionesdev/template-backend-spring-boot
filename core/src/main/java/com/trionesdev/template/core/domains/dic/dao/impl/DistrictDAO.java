package com.trionesdev.template.core.domains.dic.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.template.core.domains.dic.dao.criteria.DistrictCriteria;
import com.trionesdev.template.core.domains.dic.dao.mapper.DistrictMapper;
import com.trionesdev.template.core.domains.dic.dao.po.DistrictPO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class DistrictDAO extends ServiceImpl<DistrictMapper, DistrictPO> {
    private LambdaQueryWrapper<DistrictPO> buildQueryWrapper(final DistrictCriteria criteria) {
        final LambdaQueryWrapper<DistrictPO> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(criteria)) {
            queryWrapper.eq(StringUtils.isNoneBlank(criteria.getParentCode()), DistrictPO::getParentCode, criteria.getParentCode());
        }
        return queryWrapper;
    }

    public List<DistrictPO> selectList(final DistrictCriteria criteria) {
        return baseMapper.selectList(buildQueryWrapper(criteria));
    }

}
