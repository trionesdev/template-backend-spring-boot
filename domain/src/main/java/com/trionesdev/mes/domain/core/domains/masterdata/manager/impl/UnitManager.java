package com.trionesdev.mes.domain.core.domains.masterdata.manager.impl;

import com.trionesdev.mes.domain.core.domains.masterdata.dao.entity.Unit;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.impl.UnitDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UnitManager {
    private final UnitDAO unitDAO;

    public void create(Unit unit) {
        unitDAO.save(unit);
    }

    public void deleteById(String id) {
        unitDAO.removeById(id);
    }

    public void updateById(Unit unit) {
        unitDAO.updateById(unit);
    }

    public Optional<Unit> findById(String id) {
        return Optional.ofNullable(unitDAO.getById(id));
    }

    public List<Unit> findList() {
        return unitDAO.list();
    }

}
