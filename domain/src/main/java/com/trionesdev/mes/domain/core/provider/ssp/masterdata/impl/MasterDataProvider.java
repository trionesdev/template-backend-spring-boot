package com.trionesdev.mes.domain.core.provider.ssp.masterdata.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.criteria.ProductDefinitionCriteria;
import com.trionesdev.mes.domain.core.domains.masterdata.service.impl.ManufactureProcessService;
import com.trionesdev.mes.domain.core.domains.masterdata.service.impl.ProductDefinitionService;
import com.trionesdev.mes.domain.core.domains.masterdata.service.impl.UnitService;
import com.trionesdev.mes.domain.core.dto.masterdata.*;
import com.trionesdev.mes.domain.core.provider.ssp.masterdata.MasterDataProviderBeanConvert;
import com.trionesdev.mes.domain.core.provider.ssp.masterdata.arg.GetProductDefinitionsArg;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Component
public class MasterDataProvider {
    private final MasterDataProviderBeanConvert masterDataProviderBeanConvert;
    private final UnitService unitService;
    private final ProductDefinitionService productDefinitionService;
    private final ManufactureProcessService processService;

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
        if (CollectionUtil.isEmpty(codes)) {
            return Collections.emptyList();
        }
        return productDefinitionService.findProductsByCodes(codes);
    }

    public ManufactureProcessDTO getProcessByCode(String code) {
        return processService.findByCode(code).orElse(null);
    }

    public List<ManufactureProcessDTO> getProcessesByCodes(Collection<String> codes) {
        if (CollectionUtil.isEmpty(codes)) {
            return Collections.emptyList();
        }
        return processService.findProcessByCodes(codes);
    }

    /**
     * get process defective options by code
     *
     * @param code
     * @return
     */
    public List<DefectiveDTO> getProcessDefectiveOptionsByCode(String code) {
        return processService.findProcessDefectiveOptionsByCode(code);
    }

}
