package com.trionesdev.mes.core.domains.manufacture.dao.impl;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.StrUtil;
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
    private LambdaQueryWrapper<ManufactureWorkReportPO> queryWrapper(ManufactureWorkReportCriteria criteria) {
        var wrapper = new LambdaQueryWrapper<ManufactureWorkReportPO>();
        if (StrUtil.isNotBlank(criteria.getOrderCode())) {
            var orderConditions = ListUtil.toList();
            if (StrUtil.isNotBlank(criteria.getOrderCode())) {
                orderConditions.add(" mo.code='" + criteria.getOrderCode() + "' ");
            }
            wrapper.exists("select * from manufacture_order mo where mo.is_deleted=false and mo.id=manufacture_work_report.order_id and " + StrUtil.join(" and ", orderConditions));
        }
        return wrapper;
    }

    public PageInfo<ManufactureWorkReportPO> selectPage(ManufactureWorkReportCriteria criteria) {
        return MpPageUtils.of(this.page(MpPageUtils.page(criteria), queryWrapper(criteria)));
    }

}
