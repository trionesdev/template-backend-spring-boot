package com.trionesdev.wms.core.domains.good.dao.impl;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.wms.core.domains.good.dao.criteria.MeasureUnitCriteria;
import com.trionesdev.wms.core.domains.good.dao.mapper.MeasureUnitMapper;
import com.trionesdev.wms.core.domains.good.dao.po.MeasureUnitPO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class MeasureUnitDAO extends ServiceImpl<MeasureUnitMapper, MeasureUnitPO> {
    public LambdaQueryWrapper<MeasureUnitPO> buildQueryWrapper(MeasureUnitCriteria criteria) {
        LambdaQueryWrapper<MeasureUnitPO> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(criteria)) {
            queryWrapper.eq(StrUtil.isNotBlank(criteria.getCode()), MeasureUnitPO::getCode, criteria.getCode());
            queryWrapper.eq(StrUtil.isNotBlank(criteria.getName()), MeasureUnitPO::getName, criteria.getName());
            queryWrapper.eq(ObjUtil.isNotNull(criteria.getEnabled()), MeasureUnitPO::getEnabled, criteria.getEnabled());
            queryWrapper.in(CollectionUtils.isNotEmpty(criteria.getCodes()), MeasureUnitPO::getCode, criteria.getCodes());
        }
        queryWrapper.orderByDesc(MeasureUnitPO::getCreatedAt);
        return queryWrapper;
    }

    public List<MeasureUnitPO> selectList(MeasureUnitCriteria criteria) {
        return this.list(buildQueryWrapper(criteria));
    }

    public PageInfo<MeasureUnitPO> selectPage(MeasureUnitCriteria criteria) {
        return MpPageUtils.of(baseMapper.selectPage(MpPageUtils.page(criteria), buildQueryWrapper(criteria)));
    }

    public MeasureUnitPO getByCode(String code) {
        MeasureUnitCriteria criteria = MeasureUnitCriteria.builder().code(code).build();
        return this.getOne(buildQueryWrapper(criteria));
    }
}
