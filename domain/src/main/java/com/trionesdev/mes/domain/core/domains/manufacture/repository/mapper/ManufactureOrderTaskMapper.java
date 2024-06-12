package com.trionesdev.mes.domain.core.domains.manufacture.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.trionesdev.mes.domain.core.domains.manufacture.repository.po.ManufactureOrderTaskPO;

public interface ManufactureOrderTaskMapper extends BaseMapper<ManufactureOrderTaskPO> {
    String tableName = "manufacture_order_task";
}
