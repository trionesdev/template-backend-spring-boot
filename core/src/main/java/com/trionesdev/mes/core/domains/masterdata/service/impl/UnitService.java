package com.trionesdev.mes.core.domains.masterdata.service.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.mes.core.domains.masterdata.dao.criteria.UnitCriteria;
import com.trionesdev.mes.core.domains.masterdata.dao.po.UnitPO;
import com.trionesdev.mes.core.domains.masterdata.dto.UnitDTO;
import com.trionesdev.mes.core.domains.masterdata.internal.MasterDataBeanConvert;
import com.trionesdev.mes.core.domains.masterdata.manager.impl.UnitManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
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

    public List<UnitDTO> findListByIds(Collection<String> ids) {
        return masterDataBeanConvert.unitsEntityToDto(unitManager.findListByIds(ids));
    }

    public PageInfo<UnitPO> findPage(UnitCriteria criteria) {
        return unitManager.findPage(criteria);
    }
}
