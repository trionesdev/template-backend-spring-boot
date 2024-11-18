package com.trionesdev.wms.core.domains.warehouse.dao.impl;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.wms.core.domains.warehouse.dao.criteria.WarehouseAreaCriteria;
import com.trionesdev.wms.core.domains.warehouse.dao.mapper.WarehouseAreaMapper;
import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehouseAreaPO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class WarehouseAreaDAO extends ServiceImpl<WarehouseAreaMapper, WarehouseAreaPO> {
    public LambdaQueryWrapper<WarehouseAreaPO> buildQueryWrapper(WarehouseAreaCriteria criteria) {
        LambdaQueryWrapper<WarehouseAreaPO> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(criteria)) {
            queryWrapper.eq(StrUtil.isNotBlank(criteria.getCode()), WarehouseAreaPO::getCode, criteria.getCode());
            queryWrapper.eq(StrUtil.isNotBlank(criteria.getName()), WarehouseAreaPO::getName, criteria.getName());
            queryWrapper.eq(ObjUtil.isNotNull(criteria.getEnabled()), WarehouseAreaPO::getEnabled, criteria.getEnabled());
            queryWrapper.eq(StrUtil.isNotBlank(criteria.getWarehouseId()), WarehouseAreaPO::getWarehouseId, criteria.getWarehouseId());
        }
        queryWrapper.orderByDesc(WarehouseAreaPO::getCreatedAt);
        return queryWrapper;
    }

    public List<WarehouseAreaPO> selectList(WarehouseAreaCriteria criteria) {
        return this.list(buildQueryWrapper(criteria));
    }

    public PageInfo<WarehouseAreaPO> selectPage(WarehouseAreaCriteria criteria) {
        return MpPageUtils.of(baseMapper.selectPage(MpPageUtils.page(criteria), buildQueryWrapper(criteria)));
    }
}
