package com.trionesdev.mes.domain.core.domains.masterdata.service.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.criteria.ProductDefinitionCriteria;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.po.ProductDefinitionPO;
import com.trionesdev.mes.domain.core.domains.masterdata.internal.MasterDataBeanConvert;
import com.trionesdev.mes.domain.core.domains.masterdata.manager.impl.ProductDefinitionManager;
import com.trionesdev.mes.domain.core.dto.masterdata.ProductDefinitionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductDefinitionService {
    private final MasterDataBeanConvert masterDataBeanConvert;
    private final ProductDefinitionManager productDefinitionManager;

    public void create(ProductDefinitionPO productDefinition) {
        productDefinitionManager.create(productDefinition);
    }

    public void deleteById(String id) {
        productDefinitionManager.deleteById(id);
    }

    public void updateById(ProductDefinitionPO productDefinition) {
        productDefinitionManager.updateById(productDefinition);
    }

    public Optional<ProductDefinitionDTO> findById(String id) {
        return productDefinitionManager.findById(id).map(masterDataBeanConvert::entityToDto);
    }

    public List<ProductDefinitionDTO> findList(ProductDefinitionCriteria criteria) {
        return masterDataBeanConvert.productDefinitionsEntityToDto(productDefinitionManager.findList(criteria));
    }

    public PageInfo<ProductDefinitionDTO> findPage(ProductDefinitionCriteria criteria) {
        PageInfo<ProductDefinitionPO> page = productDefinitionManager.findPage(criteria);
        return PageUtils.of(page, masterDataBeanConvert.productDefinitionsEntityToDto(page.getRows()));
    }

}
