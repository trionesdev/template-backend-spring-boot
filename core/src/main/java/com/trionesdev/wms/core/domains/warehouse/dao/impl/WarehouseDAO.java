package com.trionesdev.wms.core.domains.warehouse.dao.impl;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.wms.core.domains.warehouse.dao.criteria.WarehouseCriteria;
import com.trionesdev.wms.core.domains.warehouse.dao.mapper.WarehouseMapper;
import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehousePO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class WarehouseDAO extends ServiceImpl<WarehouseMapper, WarehousePO> {
    public LambdaQueryWrapper<WarehousePO> buildQueryWrapper(WarehouseCriteria criteria) {
        LambdaQueryWrapper<WarehousePO> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(criteria)) {
            queryWrapper.eq(StrUtil.isNotBlank(criteria.getCode()), WarehousePO::getCode, criteria.getCode());
            queryWrapper.eq(StrUtil.isNotBlank(criteria.getName()), WarehousePO::getName, criteria.getName());
            queryWrapper.eq(ObjUtil.isNotNull(criteria.getEnabled()), WarehousePO::getEnabled, criteria.getEnabled());
        }
        queryWrapper.orderByDesc(WarehousePO::getCreatedAt);
        return queryWrapper;
    }

    public List<WarehousePO> selectList(WarehouseCriteria criteria) {
        return this.list(buildQueryWrapper(criteria));
    }

    public PageInfo<WarehousePO> selectPage(WarehouseCriteria criteria) {
        return MpPageUtils.of(baseMapper.selectPage(MpPageUtils.page(criteria), buildQueryWrapper(criteria)));
    }

}
