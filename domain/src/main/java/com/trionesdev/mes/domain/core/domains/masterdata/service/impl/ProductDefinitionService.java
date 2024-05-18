package com.trionesdev.mes.domain.core.domains.masterdata.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.mes.domain.core.domains.masterdata.entity.ProductDefinition;
import com.trionesdev.mes.domain.core.domains.masterdata.internal.MasterDataBeanConvert;
import com.trionesdev.mes.domain.core.domains.masterdata.manager.impl.ProductDefinitionManager;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.criteria.ProductDefinitionCriteria;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.ProductDefinitionPO;
import com.trionesdev.mes.domain.core.dto.masterdata.ProductDefinitionDTO;
import com.trionesdev.mes.domain.core.provider.ssp.custom.impl.CustomProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductDefinitionService {
    private final MasterDataBeanConvert convert;
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
        return productDefinitionManager.findById(id).map(convert::entityToDto);
    }

    public List<ProductDefinitionDTO> findList(ProductDefinitionCriteria criteria) {
        List<ProductDefinition> productDefinitions = productDefinitionManager.findList(criteria);
        return assembleBatch(productDefinitions);
    }

    public PageInfo<ProductDefinitionDTO> findPage(ProductDefinitionCriteria criteria) {
        PageInfo<ProductDefinition> page = productDefinitionManager.findPage(criteria);
        return PageUtils.of(page, assembleBatch(page.getRows()));
    }

    private List<ProductDefinitionDTO> assembleBatch(List<ProductDefinition> records) {
        if (CollectionUtil.isEmpty(records)) {
            return Collections.emptyList();
        }
        return records.stream().map(convert::entityToDto).collect(Collectors.toList());
    }
}
