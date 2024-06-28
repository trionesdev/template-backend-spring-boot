package com.trionesdev.mes.core.domains.masterdata.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.mes.core.domains.masterdata.dao.po.ProductMaterialPO;
import com.trionesdev.mes.core.domains.masterdata.entity.ProductBom;
import com.trionesdev.mes.core.domains.masterdata.entity.ProductDefinition;
import com.trionesdev.mes.core.domains.masterdata.manager.impl.ProductBomManager;
import com.trionesdev.mes.core.domains.masterdata.internal.MasterDataBeanConvert;
import com.trionesdev.mes.core.domains.masterdata.manager.impl.ProductDefinitionManager;
import com.trionesdev.mes.core.domains.masterdata.manager.impl.UnitManager;
import com.trionesdev.mes.core.domains.masterdata.dao.criteria.ProductDefinitionCriteria;
import com.trionesdev.mes.core.domains.masterdata.dto.ProductBomDTO;
import com.trionesdev.mes.core.domains.masterdata.dto.ProductMaterialDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductBomService {
    private final MasterDataBeanConvert convert;
    private final ProductDefinitionManager productDefinitionManager;
    private final ProductBomManager productBomManager;
    private final UnitManager unitManager;

    public void createBom(ProductBom productBom) {
        productBomManager.createBom(productBom);
    }

    public void deleteBom(String productCode) {
        productBomManager.deleteBom(productCode);
    }

    public void updateBom(ProductBom productBom) {
        productBomManager.updateBom(productBom);
    }

    public List<ProductMaterialDTO> findProductMaterials(String productCode) {
        List<ProductMaterialPO> productMaterials = productBomManager.findByProductCode(productCode);
        return assembleMaterialDtoBatch(productMaterials);
    }

    public PageInfo<ProductBomDTO> findBomPage(ProductDefinitionCriteria criteria) {
        criteria.setHasBom(true);
        PageInfo<ProductDefinition> pageInfo = productDefinitionManager.findPage(criteria);
        return PageUtils.of(pageInfo, assembleBomDtoBatch(pageInfo.getRows()));
    }

    private List<ProductMaterialDTO> assembleMaterialDtoBatch(List<ProductMaterialPO> records) {
        if (CollectionUtil.isEmpty(records)) {
            return Collections.emptyList();
        }
        Set<String> materialCodes = records.stream().map(ProductMaterialPO::getMaterialCode).collect(Collectors.toSet());
        Map<String, ProductDefinition> productDefinitionMap = productDefinitionManager.findByCodes(materialCodes).stream().collect(Collectors.toMap(ProductDefinition::getCode, v -> v));
        return records.stream().map(record -> {
            ProductMaterialDTO dto = convert.poToDto(record);
            Optional.ofNullable(productDefinitionMap.get(record.getMaterialCode())).ifPresent(product -> {
                dto.setProduct(convert.productEntityToDto(product));
            });
            return dto;
        }).collect(Collectors.toList());
    }

    private List<ProductBomDTO> assembleBomDtoBatch(List<ProductDefinition> records) {
        if (CollectionUtil.isEmpty(records)) {
            return Collections.emptyList();
        }
        return records.stream().map(product -> {
            ProductBomDTO dto = convert.bomEntityToDto(product);
            return dto;
        }).collect(Collectors.toList());
    }

}
