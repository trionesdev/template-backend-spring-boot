package com.trionesdev.mes.domain.core.domains.masterdata.repository.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.mes.domain.core.domains.masterdata.entity.ManufactureBom;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.criteria.ManufactureBomCriteria;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.ManufactureBomPO;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.mapper.ManufactureBomMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class ManufactureBomRepository extends ServiceImpl<ManufactureBomMapper, ManufactureBomPO> {
    private LambdaQueryWrapper<ManufactureBomPO> buildQueryWrapper(ManufactureBomCriteria criteria) {
        LambdaQueryWrapper<ManufactureBomPO> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(criteria)) {
            queryWrapper.eq(StrUtil.isNotBlank(criteria.getProductCode()), ManufactureBomPO::getProductCode, criteria.getProductCode());
        }
        return queryWrapper;
    }

    public List<ManufactureBomPO> selectList(ManufactureBomCriteria criteria) {
        return baseMapper.selectList(buildQueryWrapper(criteria));
    }

    public PageInfo<ManufactureBomPO> selectPage(ManufactureBomCriteria criteria) {
        return MpPageUtils.of(baseMapper.selectPage(MpPageUtils.page(criteria), buildQueryWrapper(criteria)));
    }

}
