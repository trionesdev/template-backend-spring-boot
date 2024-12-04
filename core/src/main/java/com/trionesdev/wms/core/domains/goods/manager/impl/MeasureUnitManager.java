package com.trionesdev.wms.core.domains.goods.manager.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.wms.core.domains.goods.dao.criteria.MeasureUnitCriteria;
import com.trionesdev.wms.core.domains.goods.dao.impl.MeasureUnitDAO;
import com.trionesdev.wms.core.domains.goods.dao.po.MeasureUnitPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MeasureUnitManager {
    private final MeasureUnitDAO measureUnitRepository;

    public void create(MeasureUnitPO po) {
        measureUnitRepository.save(po);
    }

    public void updateById(MeasureUnitPO po) {
        measureUnitRepository.updateById(po);
    }

    public Optional<MeasureUnitPO> findById(String id) {
        return Optional.ofNullable(measureUnitRepository.getById(id));
    }

    public Optional<MeasureUnitPO> findByCode(String code) {
        return Optional.ofNullable(measureUnitRepository.getByCode(code));
    }

    public List<MeasureUnitPO> findList(MeasureUnitCriteria criteria) {
        return measureUnitRepository.selectList(criteria);
    }

    public PageInfo<MeasureUnitPO> findPage(MeasureUnitCriteria criteria) {
        return measureUnitRepository.selectPage(criteria);
    }

    public void deleteByIds(List<String> ids) {
        measureUnitRepository.removeByIds(ids);
    }
}
