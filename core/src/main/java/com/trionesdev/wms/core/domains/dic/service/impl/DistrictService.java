package com.trionesdev.wms.core.domains.dic.service.impl;

import com.trionesdev.wms.core.domains.dic.dao.criteria.DistrictCriteria;
import com.trionesdev.wms.core.domains.dic.dao.po.DistrictPO;
import com.trionesdev.wms.core.domains.dic.manager.impl.DistrictManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DistrictService {
    private final DistrictManager districtManager;

    public List<DistrictPO> findDistricts(DistrictCriteria criteria) {
        return districtManager.findDistricts(criteria);
    }
}
