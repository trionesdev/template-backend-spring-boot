package com.trionesdev.mes.domain.core.domains.masterdata.manager.impl;

import com.trionesdev.mes.domain.core.domains.masterdata.dao.entity.ManufactureProcess;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.impl.ManufactureProcessDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ManufactureProcessManager {
    private final ManufactureProcessDAO manufactureProcessDAO;

    public void create(ManufactureProcess manufactureProcess) {
        manufactureProcessDAO.save(manufactureProcess);
    }

    public void deleteById(String id) {
        manufactureProcessDAO.removeById(id);
    }

    public void updateById(ManufactureProcess manufactureProcess) {
        manufactureProcessDAO.updateById(manufactureProcess);
    }

    public List<ManufactureProcess> findListByCodes(Collection<String> codes) {
        return manufactureProcessDAO.selectListByCodes(codes);
    }
}
