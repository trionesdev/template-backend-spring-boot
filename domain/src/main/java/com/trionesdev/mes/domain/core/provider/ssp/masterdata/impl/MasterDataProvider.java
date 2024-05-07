package com.trionesdev.mes.domain.core.provider.ssp.masterdata.impl;

import com.trionesdev.mes.domain.core.domains.masterdata.service.impl.UnitService;
import com.trionesdev.mes.domain.core.dto.masterdata.TechnologyDTO;
import com.trionesdev.mes.domain.core.dto.masterdata.UnitDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class MasterDataProvider {
    private final UnitService unitService;

    public List<UnitDTO> getUnitList() {
        return unitService.findList();
    }

    public List<TechnologyDTO> getTechnologyList() {
        return null;
    }

}
