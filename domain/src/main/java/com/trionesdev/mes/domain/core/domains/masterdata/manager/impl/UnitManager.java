package com.trionesdev.mes.domain.core.domains.masterdata.manager.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.criteria.UnitCriteria;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.UnitPO;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.impl.UnitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UnitManager {
    private final UnitRepository unitDAO;

    public void create(UnitPO unit) {
        unitDAO.save(unit);
    }

    public void deleteById(String id) {
        unitDAO.removeById(id);
    }

    public void updateById(UnitPO unit) {
        unitDAO.updateById(unit);
    }

    public Optional<UnitPO> findById(String id) {
        return Optional.ofNullable(unitDAO.getById(id));
    }

    public List<UnitPO> findList() {
        return unitDAO.list();
    }

    public PageInfo<UnitPO> findPage(UnitCriteria criteria) {
        return unitDAO.selectPage(criteria);
    }

}
