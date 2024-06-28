package com.trionesdev.mes.core.domains.manufacture.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.trionesdev.mes.core.domains.manufacture.dao.po.ManufactureOrderTaskPO;

public interface ManufactureOrderTaskMapper extends BaseMapper<ManufactureOrderTaskPO> {
    String tableName = "manufacture_order_task";
}
