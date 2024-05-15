package com.trionesdev.mes.domain.core.domains.masterdata.manager.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.criteria.UnitCriteria;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.UnitPO;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.impl.UnitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UnitManager {
    private final UnitRepository unitRepository;

    public void create(UnitPO unit) {
        unitRepository.save(unit);
    }

    public void deleteById(String id) {
        unitRepository.removeById(id);
    }

    public void updateById(UnitPO unit) {
        unitRepository.updateById(unit);
    }

    public Optional<UnitPO> findById(String id) {
        return Optional.ofNullable(unitRepository.getById(id));
    }

    public List<UnitPO> findList() {
        return unitRepository.list();
    }

    public PageInfo<UnitPO> findPage(UnitCriteria criteria) {
        return unitRepository.selectPage(criteria);
    }

    public List<UnitPO> findListByIds(Collection<String> ids){
        return unitRepository.listByIds(ids);
    }

}
