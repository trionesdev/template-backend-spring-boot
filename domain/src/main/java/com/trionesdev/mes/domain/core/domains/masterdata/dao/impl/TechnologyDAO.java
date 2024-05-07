package com.trionesdev.mes.domain.core.domains.masterdata.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.criteria.TechnologyCriteria;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.entity.Technology;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.mapper.TechnologyMapper;

import java.util.List;

@Repository
public class TechnologyDAO extends ServiceImpl<TechnologyMapper, Technology> {
    private LambdaQueryWrapper<Technology> buildQueryWrapper(TechnologyCriteria criteria) {
        LambdaQueryWrapper<Technology> queryWrapper = new LambdaQueryWrapper<>();
        return queryWrapper;
    }

    public List<Technology> selectList(TechnologyCriteria criteria) {
        return baseMapper.selectList(buildQueryWrapper(criteria));
    }

    public PageInfo<Technology> selectPage(TechnologyCriteria criteria) {
        return MpPageUtils.of(baseMapper.selectPage(MpPageUtils.page(criteria),buildQueryWrapper(criteria)));
    }

}
