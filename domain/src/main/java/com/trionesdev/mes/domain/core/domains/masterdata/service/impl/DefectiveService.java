package com.trionesdev.mes.domain.core.domains.masterdata.service.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.mes.domain.core.domains.masterdata.manager.impl.DefectiveManager;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.criteria.DefectiveCriteria;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.DefectivePO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DefectiveService {
    private final DefectiveManager defectiveManager;

    public void create(DefectivePO defective) {
        defectiveManager.create(defective);
    }

    public void deleteById(String id) {
        defectiveManager.deleteById(id);
    }

    public void updateById(DefectivePO defective) {
        defectiveManager.updateById(defective);
    }

    public Optional<DefectivePO> findById(String id) {
        return defectiveManager.findById(id);
    }

    public List<DefectivePO> findList(DefectiveCriteria defective) {
        return defectiveManager.findList(defective);
    }

    public PageInfo<DefectivePO> findPage(DefectiveCriteria defective) {
        return defectiveManager.findPage(defective);
    }

}
