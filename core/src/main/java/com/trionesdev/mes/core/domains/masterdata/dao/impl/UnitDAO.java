package com.trionesdev.mes.core.domains.masterdata.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.mes.core.domains.masterdata.dao.mapper.UnitMapper;
import com.trionesdev.mes.core.domains.masterdata.dao.criteria.UnitCriteria;
import com.trionesdev.mes.core.domains.masterdata.dao.po.UnitPO;
import org.springframework.stereotype.Repository;

@Repository
public class UnitDAO extends ServiceImpl<UnitMapper, UnitPO> {
    private LambdaQueryWrapper<UnitPO> buildQueryWrapper(UnitCriteria criteria) {
        LambdaQueryWrapper<UnitPO> queryWrapper = new LambdaQueryWrapper<>();
        return queryWrapper;
    }

    public PageInfo<UnitPO> selectPage(UnitCriteria criteria) {
        return MpPageUtils.of(baseMapper.selectPage(MpPageUtils.page(criteria), buildQueryWrapper(criteria).orderByDesc(UnitPO::getCreatedAt)));
    }
}
