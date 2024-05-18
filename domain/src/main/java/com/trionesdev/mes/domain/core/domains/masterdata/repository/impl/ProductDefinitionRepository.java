package com.trionesdev.mes.domain.core.domains.masterdata.repository.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.criteria.ProductDefinitionCriteria;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.ProductDefinitionPO;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.mapper.ProductDefinitionMapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Repository
public class ProductDefinitionRepository extends ServiceImpl<ProductDefinitionMapper, ProductDefinitionPO> {
    private LambdaQueryWrapper<ProductDefinitionPO> buildQueryWrapper(ProductDefinitionCriteria criteria) {
        LambdaQueryWrapper<ProductDefinitionPO> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(criteria)) {
            queryWrapper.eq(StrUtil.isNotBlank(criteria.getCode()), ProductDefinitionPO::getCode, criteria.getCode())
                    .eq(StrUtil.isNotBlank(criteria.getName()), ProductDefinitionPO::getName, criteria.getName())
                    .exists(Objects.nonNull(criteria.getHasBom()), "select product_code from master_data_product_material where master_data_product_definition.code=master_data_product_material.product_code")
            ;
        }
        return queryWrapper;
    }

    public List<ProductDefinitionPO> selectListByCodes(Collection<String> codes) {
        return baseMapper.selectList(new LambdaQueryWrapper<ProductDefinitionPO>().in(ProductDefinitionPO::getCode, codes));
    }

    public List<ProductDefinitionPO> selectList(ProductDefinitionCriteria criteria) {
        return baseMapper.selectList(buildQueryWrapper(criteria));
    }

    public PageInfo<ProductDefinitionPO> selectPage(ProductDefinitionCriteria criteria) {
        return MpPageUtils.of(baseMapper.selectPage(MpPageUtils.page(criteria), buildQueryWrapper(criteria)));
    }

}
