package com.trionesdev.mes.core.domains.manufacture.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.mes.core.domains.manufacture.dao.mapper.ManufactureOrderTaskReportDefectiveMapper;
import com.trionesdev.mes.core.domains.manufacture.dao.po.ManufactureOrderTaskReportDefectivePO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ManufactureOrderTaskReportDefectiveDAO extends ServiceImpl<ManufactureOrderTaskReportDefectiveMapper, ManufactureOrderTaskReportDefectivePO> {

    public void removeByReportId(String reportId) {
        baseMapper.delete(lambdaQuery().eq(ManufactureOrderTaskReportDefectivePO::getReportId, reportId));
    }

    public List<ManufactureOrderTaskReportDefectivePO> selectByReportId(String reportId) {
        return baseMapper.selectList(lambdaQuery().eq(ManufactureOrderTaskReportDefectivePO::getReportId, reportId));
    }

}
