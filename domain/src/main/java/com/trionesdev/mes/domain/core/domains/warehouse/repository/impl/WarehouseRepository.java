package com.trionesdev.mes.domain.core.domains.warehouse.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.mes.domain.core.domains.warehouse.repository.mapper.WarehouseMapper;
import com.trionesdev.mes.domain.core.domains.warehouse.repository.po.WarehousePO;
import org.springframework.stereotype.Repository;

@Repository
public class WarehouseRepository extends ServiceImpl<WarehouseMapper, WarehousePO> {
}
