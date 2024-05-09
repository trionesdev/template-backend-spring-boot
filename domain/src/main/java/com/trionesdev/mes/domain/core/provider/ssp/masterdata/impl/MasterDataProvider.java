package com.trionesdev.mes.domain.core.provider.ssp.masterdata.impl;

import com.trionesdev.mes.domain.core.domains.masterdata.dao.criteria.ProductDefinitionCriteria;
import com.trionesdev.mes.domain.core.domains.masterdata.service.impl.ProductDefinitionService;
import com.trionesdev.mes.domain.core.domains.masterdata.service.impl.UnitService;
import com.trionesdev.mes.domain.core.dto.masterdata.ProductDefinitionDTO;
import com.trionesdev.mes.domain.core.dto.masterdata.TechnologyDTO;
import com.trionesdev.mes.domain.core.dto.masterdata.UnitDTO;
import com.trionesdev.mes.domain.core.provider.ssp.masterdata.MasterDataProviderBeanConvert;
import com.trionesdev.mes.domain.core.provider.ssp.masterdata.dto.QueryProductDefinitionsArg;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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

    public List<ProductDefinitionDTO> getProductDefinitionList(QueryProductDefinitionsArg req) {
        ProductDefinitionCriteria criteria = masterDataProviderBeanConvert.from(req);
        return productDefinitionService.findList(criteria);
    }

    public List<TechnologyDTO> getTechnologyList() {
        return null;
    }

}
