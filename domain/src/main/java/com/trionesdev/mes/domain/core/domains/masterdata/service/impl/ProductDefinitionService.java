package com.trionesdev.mes.domain.core.domains.masterdata.service.impl;

import com.trionesdev.mes.domain.core.domains.masterdata.dao.criteria.ProductDefinitionCriteria;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.entity.ProductDefinition;
import com.trionesdev.mes.domain.core.domains.masterdata.manager.impl.ProductDefinitionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductDefinitionService {
    private final ProductDefinitionManager productDefinitionManager;

    public void create(ProductDefinition productDefinition) {
        productDefinitionManager.create(productDefinition);
    }

    public void deleteById(String id) {
        productDefinitionManager.deleteById(id);
    }

    public void updateById(ProductDefinition productDefinition) {
        productDefinitionManager.updateById(productDefinition);
    }

    public Optional<ProductDefinition> findById(String id) {
        return productDefinitionManager.findById(id);
    }

    public List<ProductDefinition> findList(ProductDefinitionCriteria criteria){
        return productDefinitionManager.findList(criteria);
    }

}
