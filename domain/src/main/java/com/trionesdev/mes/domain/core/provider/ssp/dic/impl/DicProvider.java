package com.trionesdev.mes.domain.core.provider.ssp.dic.impl;

import com.trionesdev.mes.domain.core.domains.dic.service.impl.UnitService;
import com.trionesdev.mes.domain.core.dto.UnitDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class DicProvider {
    private final UnitService unitService;

    public List<UnitDTO> getUnitList() {
        return unitService.findList();
    }

}
