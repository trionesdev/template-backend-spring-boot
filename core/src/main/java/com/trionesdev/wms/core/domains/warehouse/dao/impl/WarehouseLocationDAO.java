package com.trionesdev.wms.core.domains.warehouse.dao.impl;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.wms.core.domains.warehouse.dao.criteria.WarehouseLocationCriteria;
import com.trionesdev.wms.core.domains.warehouse.dao.mapper.WarehouseLocationMapper;
import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehouseLocationPO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class WarehouseLocationDAO extends ServiceImpl<WarehouseLocationMapper, WarehouseLocationPO> {

    public LambdaQueryWrapper<WarehouseLocationPO> buildQueryWrapper(WarehouseLocationCriteria criteria) {
        LambdaQueryWrapper<WarehouseLocationPO> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(criteria)) {
            queryWrapper.eq(StrUtil.isNotBlank(criteria.getCode()), WarehouseLocationPO::getCode, criteria.getCode());
            queryWrapper.eq(StrUtil.isNotBlank(criteria.getName()), WarehouseLocationPO::getName, criteria.getName());
            queryWrapper.eq(ObjUtil.isNotNull(criteria.getEnabled()), WarehouseLocationPO::getEnabled, criteria.getEnabled());
            queryWrapper.eq(StrUtil.isNotBlank(criteria.getWarehouseId()), WarehouseLocationPO::getWarehouseId, criteria.getWarehouseId());
            queryWrapper.eq(StrUtil.isNotBlank(criteria.getWarehouseAreaId()), WarehouseLocationPO::getWarehouseAreaId, criteria.getWarehouseAreaId());
        }
        queryWrapper.orderByDesc(WarehouseLocationPO::getCreatedAt);
        return queryWrapper;
    }

    public List<WarehouseLocationPO> selectList(WarehouseLocationCriteria criteria) {
        return this.list(buildQueryWrapper(criteria));
    }

    public PageInfo<WarehouseLocationPO> selectPage(WarehouseLocationCriteria criteria) {
        return MpPageUtils.of(baseMapper.selectPage(MpPageUtils.page(criteria), buildQueryWrapper(criteria)));
    }
}
