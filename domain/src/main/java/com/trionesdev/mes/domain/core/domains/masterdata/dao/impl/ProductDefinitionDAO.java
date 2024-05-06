package com.trionesdev.mes.domain.core.domains.masterdata.dao.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.criteria.ProductDefinitionCriteria;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.entity.ProductDefinition;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.mapper.ProductDefinitionMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class ProductDefinitionDAO extends ServiceImpl<ProductDefinitionMapper, ProductDefinition> {
    private LambdaQueryWrapper<ProductDefinition> buildQueryWrapper(ProductDefinitionCriteria criteria) {
        LambdaQueryWrapper<ProductDefinition> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(criteria)) {
            queryWrapper.eq(StrUtil.isNotBlank(criteria.getCode()), ProductDefinition::getCode, criteria.getCode())
                    .eq(StrUtil.isNotBlank(criteria.getName()), ProductDefinition::getName, criteria.getName())
            ;
        }
        return queryWrapper;
    }

    public List<ProductDefinition> selectList(ProductDefinitionCriteria criteria) {
        return baseMapper.selectList(buildQueryWrapper(criteria));
    }

    public PageInfo<ProductDefinition> selectPage(ProductDefinitionCriteria criteria) {
        return MpPageUtils.of(baseMapper.selectPage(MpPageUtils.page(criteria), buildQueryWrapper(criteria)));
    }

}
