package com.trionesdev.mes.core.domains.masterdata.dao.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.mes.core.domains.masterdata.dao.mapper.ProductMaterialMapper;
import com.trionesdev.mes.core.domains.masterdata.dao.po.ProductMaterialPO;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Repository
public class ProductMaterialDAO extends ServiceImpl<ProductMaterialMapper, ProductMaterialPO> {

    public void deleteByProductCode(String productCode) {
        this.baseMapper.delete(new LambdaUpdateWrapper<ProductMaterialPO>().eq(ProductMaterialPO::getProductCode, productCode));
    }

    public List<ProductMaterialPO> findByProductCodes(Collection<String> productCodes) {
        if (CollectionUtil.isEmpty(productCodes)) {
            return Collections.emptyList();
        }
        return baseMapper.selectList(new LambdaQueryWrapper<ProductMaterialPO>().in(ProductMaterialPO::getProductCode, productCodes));
    }

    public List<ProductMaterialPO> findByProductCode(String productCode) {
        return baseMapper.selectList(new LambdaQueryWrapper<ProductMaterialPO>().eq(ProductMaterialPO::getProductCode, productCode));
    }

}
