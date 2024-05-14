package com.trionesdev.mes.domain.core.domains.masterdata.service.impl;

import cn.hutool.core.util.StrUtil;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.criteria.ProductDefinitionCriteria;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.ProductDefinitionPO;
import com.trionesdev.mes.domain.core.domains.masterdata.internal.MasterDataBeanConvert;
import com.trionesdev.mes.domain.core.domains.masterdata.manager.impl.ProductDefinitionManager;
import com.trionesdev.mes.domain.core.dto.masterdata.ProductDefinitionDTO;
import com.trionesdev.mes.domain.core.provider.ssp.custom.impl.CustomProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductDefinitionService {
    private final MasterDataBeanConvert masterDataBeanConvert;
    private final ProductDefinitionManager productDefinitionManager;
    private final CustomProvider customProvider;

    public void create(ProductDefinitionPO productDefinition) {
        if (StrUtil.isBlank(productDefinition.getCode())) {
            productDefinition.setCode(customProvider.generateCode("PRODUCT_DEFINITION"));
        }
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
        List<ProductDefinitionPO> productDefinitions = productDefinitionManager.findList(criteria);
        return masterDataBeanConvert.productDefinitionsPoToDto(productDefinitions);
    }

    public PageInfo<ProductDefinitionDTO> findPage(ProductDefinitionCriteria criteria) {
        PageInfo<ProductDefinitionPO> page = productDefinitionManager.findPage(criteria);
        return PageUtils.of(page, masterDataBeanConvert.productDefinitionsPoToDto(page.getRows()));
    }

}
