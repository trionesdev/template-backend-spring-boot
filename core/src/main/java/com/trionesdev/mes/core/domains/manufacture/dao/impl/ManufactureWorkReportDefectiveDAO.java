package com.trionesdev.mes.core.domains.manufacture.dao.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.mes.core.domains.manufacture.dao.mapper.ManufactureWorkReportDefectiveMapper;
import com.trionesdev.mes.core.domains.manufacture.dao.po.ManufactureWorkReportDefectivePO;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Repository
public class ManufactureWorkReportDefectiveDAO extends ServiceImpl<ManufactureWorkReportDefectiveMapper, ManufactureWorkReportDefectivePO> {

    public void removeByReportId(String reportId) {
        baseMapper.delete(lambdaQuery().eq(ManufactureWorkReportDefectivePO::getReportId, reportId));
    }

    public List<ManufactureWorkReportDefectivePO> selectByReportId(String reportId) {
        return lambdaQuery().eq(ManufactureWorkReportDefectivePO::getReportId, reportId).list();
    }

    public List<ManufactureWorkReportDefectivePO> selectListByReportIds(Collection<String> reportIds) {
        if (CollectionUtil.isEmpty(reportIds)){
            return Collections.emptyList();
        }
        return lambdaQuery().in(ManufactureWorkReportDefectivePO::getReportId, reportIds).list();
    }

}
