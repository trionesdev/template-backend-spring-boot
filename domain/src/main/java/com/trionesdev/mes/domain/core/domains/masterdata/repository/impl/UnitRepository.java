package com.trionesdev.mes.domain.core.domains.masterdata.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.criteria.UnitCriteria;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.UnitPO;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.mapper.UnitMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UnitRepository extends ServiceImpl<UnitMapper, UnitPO> {
    private LambdaQueryWrapper<UnitPO> buildQueryWrapper(UnitCriteria criteria) {
        LambdaQueryWrapper<UnitPO> queryWrapper = new LambdaQueryWrapper<>();
        return queryWrapper;
    }

    public PageInfo<UnitPO> selectPage(UnitCriteria criteria) {
        return MpPageUtils.of(baseMapper.selectPage(MpPageUtils.page(criteria), buildQueryWrapper(criteria).orderByDesc(UnitPO::getCreatedAt)));
    }
}
