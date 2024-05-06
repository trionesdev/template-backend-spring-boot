package com.trionesdev.mes.domain.core.domains.dic.manager.impl;

import com.trionesdev.mes.domain.core.domains.dic.dao.entity.ProductDefinition;
import com.trionesdev.mes.domain.core.domains.dic.dao.impl.ProductDefinitionDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}
