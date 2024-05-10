package com.trionesdev.mes.domain.core.domains.masterdata.service.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.criteria.UnitCriteria;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.UnitPO;
import com.trionesdev.mes.domain.core.domains.masterdata.internal.MasterDataBeanConvert;
import com.trionesdev.mes.domain.core.domains.masterdata.manager.impl.UnitManager;
import com.trionesdev.mes.domain.core.dto.masterdata.UnitDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UnitService {
    private final MasterDataBeanConvert masterDataBeanConvert;
    private final UnitManager unitManager;

    public void create(UnitPO unit) {
        unitManager.create(unit);
    }

    public void deleteById(String id) {
        unitManager.deleteById(id);
    }

    public void updateById(UnitPO unit) {
        unitManager.updateById(unit);
    }

    public Optional<UnitDTO> findById(String id) {
        return unitManager.findById(id).map(masterDataBeanConvert::poToDto);
    }

    public List<UnitDTO> findList() {
        return masterDataBeanConvert.unitsEntityToDto(unitManager.findList());
    }

    public PageInfo<UnitPO> findPage(UnitCriteria criteria) {
        return unitManager.findPage(criteria);
    }
}
