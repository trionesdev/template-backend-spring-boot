package com.trionesdev.mes.domain.core.domains.dic.service.impl;

import com.trionesdev.mes.domain.core.domains.dic.dao.entity.Unit;
import com.trionesdev.mes.domain.core.domains.dic.internal.DicBeanConvert;
import com.trionesdev.mes.domain.core.domains.dic.manager.impl.UnitManager;
import com.trionesdev.mes.domain.core.dto.UnitDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UnitService {
    private final DicBeanConvert dicBeanConvert;
    private final UnitManager unitManager;

    public void create(Unit unit) {
        unitManager.create(unit);
    }

    public void deleteById(String id) {
        unitManager.deleteById(id);
    }

    public void updateById(Unit unit) {
        unitManager.updateById(unit);
    }

    public Optional<UnitDTO> findById(String id) {
        return unitManager.findById(id).map(dicBeanConvert::entityToDto);
    }

    public List<UnitDTO> findList() {
        return dicBeanConvert.unitsEntityToDto(unitManager.findList());
    }
}
