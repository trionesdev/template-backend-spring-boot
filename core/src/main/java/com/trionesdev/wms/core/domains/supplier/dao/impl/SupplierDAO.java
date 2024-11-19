package com.trionesdev.wms.core.domains.supplier.dao.impl;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.wms.core.domains.supplier.dao.criteria.SupplierCriteria;
import com.trionesdev.wms.core.domains.supplier.dao.mapper.SupplierMapper;
import com.trionesdev.wms.core.domains.supplier.dao.po.SupplierPO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class SupplierDAO extends ServiceImpl<SupplierMapper, SupplierPO> {
    public LambdaQueryWrapper<SupplierPO> buildQueryWrapper(SupplierCriteria criteria) {
        LambdaQueryWrapper<SupplierPO> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(criteria)) {
            queryWrapper.eq(StrUtil.isNotBlank(criteria.getCode()), SupplierPO::getCode, criteria.getCode());
            queryWrapper.eq(StrUtil.isNotBlank(criteria.getName()), SupplierPO::getName, criteria.getName());
            queryWrapper.eq(StrUtil.isNotBlank(criteria.getContactName()), SupplierPO::getContactName, criteria.getContactName());
            queryWrapper.eq(ObjUtil.isNotNull(criteria.getEnabled()), SupplierPO::getEnabled, criteria.getEnabled());
        }
        queryWrapper.orderByDesc(SupplierPO::getCreatedAt);
        return queryWrapper;
    }

    public List<SupplierPO> selectList(SupplierCriteria criteria) {
        return this.list(buildQueryWrapper(criteria));
    }

    public PageInfo<SupplierPO> selectPage(SupplierCriteria criteria) {
        return MpPageUtils.of(baseMapper.selectPage(MpPageUtils.page(criteria), buildQueryWrapper(criteria)));
    }
}
