package com.trionesdev.mes.core.domains.masterdata.manager.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.mes.core.domains.masterdata.dao.criteria.UnitCriteria;
import com.trionesdev.mes.core.domains.masterdata.dao.impl.UnitDAO;
import com.trionesdev.mes.core.domains.masterdata.dao.po.UnitPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UnitManager {
    private final UnitDAO unitDAO;

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

    public List<UnitPO> findListByIds(Collection<String> ids){
        return unitDAO.listByIds(ids);
    }

}
