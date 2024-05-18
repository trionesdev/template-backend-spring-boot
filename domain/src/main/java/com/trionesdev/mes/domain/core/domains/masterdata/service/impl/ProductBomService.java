package com.trionesdev.mes.domain.core.domains.masterdata.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.PageUtil;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.mes.domain.core.domains.masterdata.entity.ProductBom;
import com.trionesdev.mes.domain.core.domains.masterdata.internal.MasterDataBeanConvert;
import com.trionesdev.mes.domain.core.domains.masterdata.manager.impl.ProductBomManager;
import com.trionesdev.mes.domain.core.domains.masterdata.manager.impl.ProductDefinitionManager;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.criteria.ProductDefinitionCriteria;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.ProductDefinitionPO;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.ProductMaterialPO;
import com.trionesdev.mes.domain.core.dto.masterdata.ProductBomDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductBomService {
    private final MasterDataBeanConvert convert;
    private final ProductDefinitionManager productDefinitionManager;
    private final ProductBomManager productBomManager;

    public void createBom(ProductBom productBom) {
        productBomManager.createBom(productBom);
    }

    public void deleteBom(String productCode) {
        productBomManager.deleteBom(productCode);
    }

    public void updateBom(ProductBom productBom) {
        productBomManager.updateBom(productBom);
    }

    public PageInfo<ProductBomDTO> findBomPage(ProductDefinitionCriteria criteria) {
        criteria.setHasBom(true);
        PageInfo<ProductDefinitionPO> pageInfo = productDefinitionManager.findPage(criteria);
        return PageUtils.of(pageInfo, assembleDtoBatch(pageInfo.getRows()));
    }

    private List<ProductBomDTO> assembleDtoBatch(List<ProductDefinitionPO> records) {
        if (CollectionUtil.isEmpty(records)){
            return Collections.emptyList();
        }
        Set<String> productCodes = records.stream().map(ProductDefinitionPO::getCode).collect(Collectors.toSet());
        Map<String,List<ProductMaterialPO>> materialMap = productBomManager.findByProductCodes(productCodes).stream().collect(Collectors.groupingBy(ProductMaterialPO::getProductCode));
        return records.stream().map(product->{
            ProductBomDTO dto = convert.bomEntityToDto(product);

            return dto;
        }).collect(Collectors.toList());
    }

}
