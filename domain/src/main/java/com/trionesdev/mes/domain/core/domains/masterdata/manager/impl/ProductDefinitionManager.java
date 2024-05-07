package com.trionesdev.mes.domain.core.domains.masterdata.manager.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.criteria.ProductDefinitionCriteria;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.entity.ProductDefinition;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.impl.ProductDefinitionDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductDefinitionManager {
    private final ProductDefinitionDAO productDefinitionDAO;

    public void create(ProductDefinition productDefinition) {
        productDefinitionDAO.save(productDefinition);
    }

    public void deleteById(String id) {
        productDefinitionDAO.removeById(id);
    }

    public void updateById(ProductDefinition productDefinition) {
        productDefinitionDAO.updateById(productDefinition);
    }

    public Optional<ProductDefinition> findById(String id) {
        return Optional.ofNullable(productDefinitionDAO.getById(id));
    }

    public List<ProductDefinition> findList(ProductDefinitionCriteria criteria) {
        return productDefinitionDAO.selectList(criteria);
    }

    public PageInfo<ProductDefinition> findPage(ProductDefinitionCriteria criteria) {
        return productDefinitionDAO.selectPage(criteria);
    }

}
