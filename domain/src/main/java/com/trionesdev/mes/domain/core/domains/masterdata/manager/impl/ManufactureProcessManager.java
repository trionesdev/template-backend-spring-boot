package com.trionesdev.mes.domain.core.domains.masterdata.manager.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.criteria.ManufactureProcessCriteria;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.po.ManufactureProcessPO;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.impl.ManufactureProcessDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ManufactureProcessManager {
    private final ManufactureProcessDAO manufactureProcessDAO;

    public void create(ManufactureProcessPO manufactureProcess) {
        manufactureProcessDAO.save(manufactureProcess);
    }

    public void deleteById(String id) {
        manufactureProcessDAO.removeById(id);
    }

    public void updateById(ManufactureProcessPO manufactureProcess) {
        manufactureProcessDAO.updateById(manufactureProcess);
    }

    public Optional<ManufactureProcessPO> findById(String id) {
        return Optional.ofNullable(manufactureProcessDAO.getById(id));
    }

    public List<ManufactureProcessPO> findList(ManufactureProcessCriteria criteria) {
        return manufactureProcessDAO.selectList(criteria);
    }

    public PageInfo<ManufactureProcessPO> findPage(ManufactureProcessCriteria criteria) {
        return manufactureProcessDAO.selectPage(criteria);
    }

    public List<ManufactureProcessPO> findListByCodes(Collection<String> codes) {
        return manufactureProcessDAO.selectListByCodes(codes);
    }
}
