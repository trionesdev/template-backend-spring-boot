package com.trionesdev.mes.core.domains.masterdata.manager.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.mes.core.domains.masterdata.dao.criteria.DefectiveCriteria;
import com.trionesdev.mes.core.domains.masterdata.dao.impl.DefectiveDAO;
import com.trionesdev.mes.core.domains.masterdata.dao.po.DefectivePO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
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

    public List<DefectivePO> findListByCodes(Collection<String> codes) {
        if (CollectionUtil.isEmpty(codes)){
            return Collections.emptyList();
        }
        return defectiveDAO.selectListByCodes(codes);
    }

    public List<DefectivePO> findList(DefectiveCriteria criteria) {
        return defectiveDAO.selectList(criteria);
    }

    public PageInfo<DefectivePO> findPage(DefectiveCriteria criteria) {
        return defectiveDAO.selectPage(criteria);
    }

}
