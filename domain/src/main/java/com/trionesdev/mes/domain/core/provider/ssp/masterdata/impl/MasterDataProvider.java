package com.trionesdev.mes.domain.core.provider.ssp.masterdata.impl;

import com.trionesdev.mes.domain.core.domains.masterdata.repository.criteria.ProductDefinitionCriteria;
import com.trionesdev.mes.domain.core.domains.masterdata.service.impl.ProductDefinitionService;
import com.trionesdev.mes.domain.core.domains.masterdata.service.impl.UnitService;
import com.trionesdev.mes.domain.core.dto.masterdata.ProcessFlowDTO;
import com.trionesdev.mes.domain.core.dto.masterdata.ProductDefinitionDTO;
import com.trionesdev.mes.domain.core.dto.masterdata.UnitDTO;
import com.trionesdev.mes.domain.core.provider.ssp.masterdata.MasterDataProviderBeanConvert;
import com.trionesdev.mes.domain.core.provider.ssp.masterdata.arg.GetProductDefinitionsArg;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Component
public class MasterDataProvider {
    private final MasterDataProviderBeanConvert masterDataProviderBeanConvert;
    private final UnitService unitService;
    private final ProductDefinitionService productDefinitionService;

    public List<UnitDTO> getUnitList() {
        return unitService.findList();
    }

    public List<ProductDefinitionDTO> getProductDefinitionList(GetProductDefinitionsArg arg) {
        ProductDefinitionCriteria criteria = masterDataProviderBeanConvert.from(arg);
        return productDefinitionService.findList(criteria);
    }

    public ProductDefinitionDTO getProductByCode(String code) {
        return productDefinitionService.findByCode(code).orElse(null);
    }

    public List<ProductDefinitionDTO> getProductsByCodes(Collection<String> codes) {
        return productDefinitionService.findProductsByCodes(codes);
    }

    public List<ProcessFlowDTO> getTechnologyList() {
        return null;
    }

}
