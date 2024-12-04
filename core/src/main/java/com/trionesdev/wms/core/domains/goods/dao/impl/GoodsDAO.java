package com.trionesdev.wms.core.domains.goods.dao.impl;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.wms.core.domains.goods.dao.criteria.GoodsCriteria;
import com.trionesdev.wms.core.domains.goods.dao.mapper.GoodsMapper;
import com.trionesdev.wms.core.domains.goods.dao.po.GoodsPO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class GoodsDAO extends ServiceImpl<GoodsMapper, GoodsPO> {
    public LambdaQueryWrapper<GoodsPO> buildQueryWrapper(GoodsCriteria criteria) {
        LambdaQueryWrapper<GoodsPO> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(criteria)) {
            queryWrapper.eq(StrUtil.isNotBlank(criteria.getCode()), GoodsPO::getCode, criteria.getCode());
            queryWrapper.eq(StrUtil.isNotBlank(criteria.getName()), GoodsPO::getName, criteria.getName());
            queryWrapper.eq(ObjUtil.isNotNull(criteria.getEnabled()), GoodsPO::getEnabled, criteria.getEnabled());
        }
        queryWrapper.orderByDesc(GoodsPO::getCreatedAt);
        return queryWrapper;
    }

    public List<GoodsPO> selectList(GoodsCriteria criteria) {
        return this.list(buildQueryWrapper(criteria));
    }

    public PageInfo<GoodsPO> selectPage(GoodsCriteria criteria) {
        return MpPageUtils.of(baseMapper.selectPage(MpPageUtils.page(criteria), buildQueryWrapper(criteria)));
    }
}
