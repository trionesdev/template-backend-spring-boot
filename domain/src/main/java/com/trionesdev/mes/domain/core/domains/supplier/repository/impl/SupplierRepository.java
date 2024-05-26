package com.trionesdev.mes.domain.core.domains.supplier.repository.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.mes.domain.core.domains.supplier.repository.criteria.SupplierCriteria;
import com.trionesdev.mes.domain.core.domains.supplier.repository.mapper.SupplierMapper;
import com.trionesdev.mes.domain.core.domains.supplier.repository.po.SupplierPO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class SupplierRepository extends ServiceImpl<SupplierMapper, SupplierPO> {

    private LambdaQueryWrapper<SupplierPO> buildQueryWrapper(SupplierCriteria criteria) {
        LambdaQueryWrapper<SupplierPO> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(criteria)) {
            queryWrapper.eq(StrUtil.isNotBlank(criteria.getCode()), SupplierPO::getCode, criteria.getCode());
        }
        return queryWrapper;
    }

    public List<SupplierPO> selectList(SupplierCriteria criteria) {
        return this.list(buildQueryWrapper(criteria));
    }

    public PageInfo<SupplierPO> selectPage(SupplierCriteria criteria) {
        return MpPageUtils.of(baseMapper.selectPage(MpPageUtils.page(criteria), buildQueryWrapper(criteria)));
    }

}
