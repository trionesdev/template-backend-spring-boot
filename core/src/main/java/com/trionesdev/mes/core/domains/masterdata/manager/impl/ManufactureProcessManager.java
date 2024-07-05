package com.trionesdev.mes.core.domains.masterdata.manager.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.mes.core.domains.masterdata.dao.criteria.ManufactureProcessCriteria;
import com.trionesdev.mes.core.domains.masterdata.dao.impl.DefectiveDAO;
import com.trionesdev.mes.core.domains.masterdata.dao.po.DefectivePO;
import com.trionesdev.mes.core.domains.masterdata.dao.po.ManufactureProcessPO;
import com.trionesdev.mes.core.domains.masterdata.dao.impl.ManufactureProcessDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ManufactureProcessManager {
    private final ManufactureProcessDAO processDAO;
    private final DefectiveDAO defectiveDAO;

    public void create(ManufactureProcessPO manufactureProcess) {
        processDAO.save(manufactureProcess);
    }

    public void deleteById(String id) {
        processDAO.removeById(id);
    }

    public void updateById(ManufactureProcessPO manufactureProcess) {
        processDAO.updateById(manufactureProcess);
    }

    public Optional<ManufactureProcessPO> findById(String id) {
        return Optional.ofNullable(processDAO.getById(id));
    }

    public Optional<ManufactureProcessPO> findByCode(String code) {
        return Optional.ofNullable(processDAO.selectByCode(code));
    }

    public List<ManufactureProcessPO> findList(ManufactureProcessCriteria criteria) {
        return processDAO.selectList(criteria);
    }

    public PageInfo<ManufactureProcessPO> findPage(ManufactureProcessCriteria criteria) {
        return processDAO.selectPage(criteria);
    }

    public List<ManufactureProcessPO> findListByCodes(Collection<String> codes) {
        return processDAO.selectListByCodes(codes);
    }

    public List<DefectivePO> findDefectiveOptionsByCode(String code) {
        return Optional.ofNullable(processDAO.selectByCode(code)).map(po -> {
            return defectiveDAO.selectListByCodes(po.getDefectiveCodes());
        }).orElse(Collections.emptyList());
    }
}
