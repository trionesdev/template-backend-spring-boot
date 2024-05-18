package com.trionesdev.mes.domain.core.domains.masterdata.manager.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.mes.domain.core.domains.masterdata.entity.ProductDefinition;
import com.trionesdev.mes.domain.core.domains.masterdata.internal.MasterDataBeanConvert;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.criteria.ProductDefinitionCriteria;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.impl.ProcessFlowRepository;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.impl.ProductDefinitionRepository;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.impl.UnitRepository;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.ProductDefinitionPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductDefinitionManager {
    private final MasterDataBeanConvert convert;
    private final ProductDefinitionRepository productDefinitionDAO;
    private final UnitRepository unitRepository;
    private final ProcessFlowRepository processFlowRepository;

    public void create(ProductDefinitionPO productDefinition) {
        productDefinitionDAO.save(productDefinition);
    }

    public void deleteById(String id) {
        productDefinitionDAO.removeById(id);
    }

    public void updateById(ProductDefinitionPO productDefinition) {
        productDefinitionDAO.updateById(productDefinition);
    }

    public Optional<ProductDefinitionPO> findById(String id) {
        return Optional.ofNullable(productDefinitionDAO.getById(id));
    }

    public List<ProductDefinition> findByCodes(Collection<String> codes) {
        return assembleEntityBatch(productDefinitionDAO.selectListByCodes(codes));
    }

    public List<ProductDefinition> findList(ProductDefinitionCriteria criteria) {
        return assembleEntityBatch(productDefinitionDAO.selectList(criteria));
    }

    public PageInfo<ProductDefinition> findPage(ProductDefinitionCriteria criteria) {
        PageInfo<ProductDefinitionPO> pageInfo = productDefinitionDAO.selectPage(criteria);
        return PageUtils.of(pageInfo, assembleEntityBatch(pageInfo.getRows()));
    }

    public List<ProductDefinition> assembleEntityBatch(List<ProductDefinitionPO> records) {
        if (CollectionUtil.isEmpty(records)) {
            return Collections.emptyList();
        }
        Set<String> unitIds = records.stream().map(ProductDefinitionPO::getUnitId).collect(Collectors.toSet());
        Map<String, ProductDefinition.Unit> unitMap = unitRepository.listByIds(unitIds).stream().map(unit -> ProductDefinition.Unit.builder().id(unit.getId()).name(unit.getName()).build()).collect(Collectors.toMap(ProductDefinition.Unit::getId, Function.identity()));
        Set<String> processFlowCodes = records.stream().map(ProductDefinitionPO::getProcessFlowCode).collect(Collectors.toSet());
        Map<String, ProductDefinition.ProcessFlow> processFlowMap = processFlowRepository.selectListByCodes(processFlowCodes).stream().map(processFlow -> ProductDefinition.ProcessFlow.builder().code(processFlow.getCode()).name(processFlow.getName()).build()).collect(Collectors.toMap(ProductDefinition.ProcessFlow::getCode, Function.identity()));
        return records.stream().map(record -> {
            ProductDefinition productDefinition = convert.poToEntity(record);
            productDefinition.setUnit(unitMap.get(record.getUnitId()));
            productDefinition.setProcessFlow(processFlowMap.get(record.getProcessFlowCode()));
            return productDefinition;
        }).collect(Collectors.toList());
    }

}
