package com.trionesdev.mes.core.domains.masterdata.service.impl;

import cn.hutool.core.util.StrUtil;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.mes.core.domains.custom.provider.impl.CustomProvider;
import com.trionesdev.mes.core.domains.masterdata.dao.po.DefectivePO;
import com.trionesdev.mes.core.domains.masterdata.manager.impl.DefectiveManager;
import com.trionesdev.mes.core.domains.masterdata.dao.criteria.DefectiveCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DefectiveService {
    private final DefectiveManager defectiveManager;
    private final CustomProvider customProvider;

    public void create(DefectivePO defective) {
        if (StrUtil.isBlank(defective.getCode())) {
            defective.setCode(customProvider.generateCode("DEFECTIVE"));
        }
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
