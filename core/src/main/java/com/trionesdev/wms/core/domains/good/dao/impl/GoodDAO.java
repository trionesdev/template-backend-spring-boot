package com.trionesdev.wms.core.domains.good.dao.impl;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.wms.core.domains.good.dao.criteria.GoodCriteria;
import com.trionesdev.wms.core.domains.good.dao.mapper.GoodMapper;
import com.trionesdev.wms.core.domains.good.dao.po.GoodPO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class GoodDAO extends ServiceImpl<GoodMapper, GoodPO> {
    public LambdaQueryWrapper<GoodPO> buildQueryWrapper(GoodCriteria criteria) {
        LambdaQueryWrapper<GoodPO> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(criteria)) {
            queryWrapper.eq(StrUtil.isNotBlank(criteria.getCode()), GoodPO::getCode, criteria.getCode());
            queryWrapper.eq(StrUtil.isNotBlank(criteria.getName()), GoodPO::getName, criteria.getName());
            queryWrapper.eq(ObjUtil.isNotNull(criteria.getEnabled()), GoodPO::getEnabled, criteria.getEnabled());
        }
        queryWrapper.orderByDesc(GoodPO::getCreatedAt);
        return queryWrapper;
    }

    public List<GoodPO> selectList(GoodCriteria criteria) {
        return this.list(buildQueryWrapper(criteria));
    }

    public PageInfo<GoodPO> selectPage(GoodCriteria criteria) {
        return MpPageUtils.of(baseMapper.selectPage(MpPageUtils.page(criteria), buildQueryWrapper(criteria)));
    }
}
