package com.trionesdev.template.core.domains.dic.manager.impl;

import com.trionesdev.template.core.domains.dic.dao.criteria.DistrictCriteria;
import com.trionesdev.template.core.domains.dic.dao.impl.DistrictDAO;
import com.trionesdev.template.core.domains.dic.dao.po.DistrictPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DistrictManager {
    private final DistrictDAO districtDAO;

    public void saveBatch(List<DistrictPO> districts) {
        districtDAO.saveBatch(districts);
    }

    public Optional<DistrictPO> findById(String id) {
        return Optional.ofNullable(districtDAO.getById(id));
    }

    public List<DistrictPO> findDistricts(DistrictCriteria criteria) {
        return districtDAO.selectList(criteria);
    }

}
