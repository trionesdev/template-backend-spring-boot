package com.trionesdev.wms.core.domains.good.service.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.wms.core.domains.good.dao.criteria.MeasureUnitCriteria;
import com.trionesdev.wms.core.domains.good.dao.po.MeasureUnitPO;
import com.trionesdev.wms.core.domains.good.manager.impl.MeasureUnitManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MeasureUnitService {

    private final MeasureUnitManager measureUnitManager;

    public void create(MeasureUnitPO po) {
        measureUnitManager.create(po);
    }

    public void updateById(MeasureUnitPO po) {
        measureUnitManager.updateById(po);
    }

    public Optional<MeasureUnitPO> findById(String id) {
        return measureUnitManager.findById(id);
    }

    public List<MeasureUnitPO> findList(MeasureUnitCriteria criteria) {
        return measureUnitManager.findList(criteria);
    }

    public PageInfo<MeasureUnitPO> findPage(MeasureUnitCriteria criteria) {
        return measureUnitManager.findPage(criteria);
    }

    public void deleteByIds(List<String> ids) {
        measureUnitManager.deleteByIds(ids);
    }
}
