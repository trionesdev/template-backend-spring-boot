package com.trionesdev.mes.domain.core.domains.masterdata.manager.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.criteria.ProductDefinitionCriteria;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.ProductDefinitionPO;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.impl.ProductDefinitionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductDefinitionManager {

    private final ProductDefinitionRepository productDefinitionDAO;

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

    public List<ProductDefinitionPO> findList(ProductDefinitionCriteria criteria) {
        return productDefinitionDAO.selectList(criteria);
    }

    public PageInfo<ProductDefinitionPO> findPage(ProductDefinitionCriteria criteria) {
        return productDefinitionDAO.selectPage(criteria);
    }

}
