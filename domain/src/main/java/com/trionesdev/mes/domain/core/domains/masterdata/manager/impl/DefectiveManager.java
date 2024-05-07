package com.trionesdev.mes.domain.core.domains.masterdata.manager.impl;

import com.trionesdev.mes.domain.core.domains.masterdata.dao.po.DefectivePO;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.impl.DefectiveDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DefectiveManager {
    private final DefectiveDAO defectiveDAO;

    public void create(DefectivePO defective) {
        defectiveDAO.save(defective);
    }

    public void deleteById(String id) {
        defectiveDAO.removeById(id);
    }

    public void updateById(DefectivePO defective) {
        defectiveDAO.updateById(defective);
    }

    public Optional<DefectivePO> findById(String id) {
        return Optional.ofNullable(defectiveDAO.getById(id));
    }

    public List<DefectivePO> findList() {
        return defectiveDAO.list();
    }

}
