package com.trionesdev.mes.core.domains.manufacture.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.mes.core.domains.manufacture.dao.criteria.ManufactureWorkReportCriteria;
import com.trionesdev.mes.core.domains.manufacture.dao.mapper.ManufactureWorkReportMapper;
import com.trionesdev.mes.core.domains.manufacture.dao.po.ManufactureWorkReportPO;
import org.springframework.stereotype.Repository;

@Repository
public class ManufactureWorkReportDAO extends ServiceImpl<ManufactureWorkReportMapper, ManufactureWorkReportPO> {
    private LambdaQueryWrapper<ManufactureWorkReportPO> queryWrapper(){
        var wrapper = new LambdaQueryWrapper<ManufactureWorkReportPO>();
        return wrapper;
    }

    public PageInfo<ManufactureWorkReportPO> selectPage(ManufactureWorkReportCriteria criteria){
        return MpPageUtils.of(this.page(MpPageUtils.page(criteria), queryWrapper()));
    }

}
